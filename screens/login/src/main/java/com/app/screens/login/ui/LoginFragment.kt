package com.app.screens.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.app.components.ui.fragment.BaseFragment
import com.app.components.validators.domain.stateerror.EmailValidationResult
import com.app.components.validators.domain.stateerror.PhoneValidationResult
import com.app.screens.login.R
import com.app.screens.login.databinding.LoginFragmentBinding
import com.app.screens.login.presentation.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class LoginFragment : BaseFragment<LoginFragmentBinding>(R.layout.login_fragment) {

	companion object {

		fun newInstance() = LoginFragment()
	}

	override fun getBinding(
		inflater: LayoutInflater,
		container: ViewGroup?
	): LoginFragmentBinding =
		LoginFragmentBinding.inflate(inflater, container, false)

	private val viewModel: LoginViewModel by viewModel()

	private val maxEmailLength: Int = 64
	private val maskPhone = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
	private val maskEmail = MaskImpl.createNonTerminated(Array(maxEmailLength) { PredefinedSlots.any() })
	private val maskWatcher = MaskFormatWatcher(maskEmail)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		disableEditTextFullScreenKeyboard()
		setObservers()
		setListeners()
	}

	private fun disableEditTextFullScreenKeyboard() {
		with(binding) {
			loginEdit.imeOptions =
				loginEdit.imeOptions or EditorInfo.IME_FLAG_NO_EXTRACT_UI
			loginPasswordEdit.imeOptions =
				loginPasswordEdit.imeOptions or EditorInfo.IME_FLAG_NO_EXTRACT_UI
		}
	}

	private fun setListeners() {
		with(binding) {
			loginEdit.doOnTextChanged { text, _, _, _ ->
				viewModel.sendIntent(LoginIntent.InputLoginText(text.toString()))
			}
			loginPasswordEdit.doOnTextChanged { text, _, _, _ ->
				viewModel.sendIntent(LoginIntent.InputPasswordText(text.toString()))
			}
			logInButton.setOnClickListener {
				viewModel.sendIntent(LoginIntent.PressLoginButton)
			}
			loginInput.setEndIconOnClickListener {
				viewModel.sendIntent(LoginIntent.ChangeLoginTextType)
				loginEdit.setText(requireContext().resources.getString(R.string.login_empty_string))
			}
			loginInput.setErrorIconOnClickListener {
				viewModel.sendIntent(LoginIntent.ChangeLoginTextType)
				loginEdit.setText(requireContext().resources.getString(R.string.login_empty_string))
			}
		}
	}

	private fun setObservers() {
		viewModel.state.observe(viewLifecycleOwner, ::handleState)
	}

	private fun handleState(state: LoginState) {
		when (state) {
			is LoginState.Content    -> renderContent(state)
			LoginState.Initial       -> renderInitial()
			LoginState.Loading       -> renderLoading()
			LoginState.CompleteLogIn -> Unit
			LoginState.FailedLogIn   -> renderLoginFailed()
		}
	}

	private fun renderContent(state: LoginState.Content) {
		with(binding) {
			content.visibility = View.VISIBLE
			progressBar.visibility = View.INVISIBLE

			when (state.errorLogin) {
				is LoginErrorFieldState.EmailError -> {
					when (state.errorLogin.errorType) {
						EmailValidationResult.DomainLengthError -> binding.loginInput.error = resources.getText(R.string.login_domain_length_email_error)
						EmailValidationResult.Empty             -> binding.loginInput.error = resources.getText(R.string.login_empty_email_error)
						EmailValidationResult.FormatError       -> binding.loginInput.error = resources.getText(R.string.login_format_email_error)
						EmailValidationResult.LengthError       -> binding.loginInput.error = resources.getText(R.string.login_length_email_error)
						else                                    -> Unit
					}
				}

				is LoginErrorFieldState.PhoneError -> {
					when (state.errorLogin.errorType) {
						PhoneValidationResult.Empty       -> binding.loginInput.error = resources.getText(R.string.login_empty_phone_error)
						PhoneValidationResult.FormatError -> binding.loginInput.error = resources.getText(R.string.login_format_phone_error)
						PhoneValidationResult.LengthError -> binding.loginInput.error = resources.getText(R.string.login_format_phone_error)
						else                              -> Unit
					}
				}

				LoginErrorFieldState.NoError       -> {
					binding.loginInput.error = null
				}
			}

			when (state.errorPassword) {
				PasswordErrorFieldState.PasswordError -> {
					binding.loginPasswordInput.error = resources.getText(R.string.login_empty_password_error)
				}

				PasswordErrorFieldState.NoError       -> {
					binding.loginPasswordInput.error = null
				}
			}

			if (state.loginType != state.previousLoginType) {
				if (state.loginType == LoginTypes.LoginWithPhone) {
					with(binding) {
						loginEdit.inputType = EditorInfo.TYPE_CLASS_PHONE
						loginInput.hint = resources.getString(R.string.login_phone)
						loginInput.setEndIconDrawable(R.drawable.icon_email)
						maskWatcher.swapMask(maskPhone)
						maskWatcher.refreshMask()
						maskWatcher.installOn(binding.loginEdit)
					}
				} else {
					with(binding) {
						loginEdit.inputType = EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
						loginInput.hint = resources.getString(R.string.login_email)
						loginInput.setEndIconDrawable(R.drawable.icon_phone)
						maskWatcher.swapMask(maskEmail)
						maskWatcher.refreshMask()
						maskWatcher.installOn(binding.loginEdit)
					}
				}
			}
		}
	}

	private fun renderInitial() {
		renderLoading()
	}

	private fun renderLoading() {
		with(binding) {
			content.visibility = View.INVISIBLE
			progressBar.visibility = View.VISIBLE
		}
	}

	private fun renderLoginFailed() {
		Toast.makeText(requireContext(), "Простите, во время входа произошла ошибка, возможно вы ввели неверные данные", Toast.LENGTH_LONG).show()
	}
}