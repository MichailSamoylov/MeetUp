package com.app.screens.login.presentation

import com.app.components.presentation.viewmodel.BaseViewModel
import com.app.components.presentation.viewmodel.launch
import com.app.components.validators.domain.email.ValidateEmailUseCase
import com.app.components.validators.domain.phone.ValidatePhoneUseCase
import com.app.components.validators.domain.stateerror.EmailValidationResult
import com.app.components.validators.domain.stateerror.PhoneValidationResult
import com.app.screens.login.domain.entity.LoginEntity
import com.app.screens.login.domain.usecase.LoginUseCase
import com.app.screens.login.ui.LoginIntent

class LoginViewModel(
	private val router: LoginRouter,
	private val loginUseCase: LoginUseCase,
	private val validatePhoneUseCase: ValidatePhoneUseCase,
	private val validateEmailUseCase: ValidateEmailUseCase,
) : BaseViewModel<LoginState, LoginIntent>() {

	private companion object {

		const val EMPTY_TEXT = ""

		val emptyState = LoginState.Content(
			loginText = EMPTY_TEXT,
			passwordText = EMPTY_TEXT,
			loginType = LoginTypes.LoginWithEmail,
			errorLogin = LoginErrorFieldState.NoError,
			errorPassword = PasswordErrorFieldState.NoError,
			previousLoginType = LoginTypes.LoginWithEmail
		)
	}

	override fun handleAction(intent: LoginIntent) {
		when (intent) {
			is LoginIntent.InputLoginText -> setLoginText(intent.loginText)
			is LoginIntent.InputPasswordText -> setPasswordText(intent.passwordText)
			LoginIntent.PressLoginButton -> tryLogin()
			LoginIntent.ChangeLoginTextType -> changeLoginType()
		}
	}

	init {
		initializeState()
	}

	private fun initializeState() {
		_state.value = emptyState.copy()
	}

	private fun changeLoginType() {
		val currentState = _state.value as? LoginState.Content ?: return
		setLoginText(EMPTY_TEXT)
		if (currentState.loginType == LoginTypes.LoginWithPhone) {
			_state.value = currentState.copy(loginType = LoginTypes.LoginWithEmail, previousLoginType = currentState.loginType)
			_state.value = currentState.copy(loginType = LoginTypes.LoginWithEmail, previousLoginType = LoginTypes.LoginWithEmail)
		} else {
			_state.value = currentState.copy(loginType = LoginTypes.LoginWithPhone, previousLoginType = currentState.loginType)
			_state.value = currentState.copy(loginType = LoginTypes.LoginWithPhone, previousLoginType = LoginTypes.LoginWithPhone)
		}
	}

	private fun setLoginText(text: String) {
		val currentState = (_state.value as? LoginState.Content ?: return).copy(errorLogin = LoginErrorFieldState.NoError)
		if (currentState.loginText != text)
			_state.value = currentState.copy(loginText = text)
	}

	private fun setPasswordText(text: String) {
		val currentState = (_state.value as? LoginState.Content ?: return).copy(errorPassword = PasswordErrorFieldState.NoError)
		_state.value = currentState.copy(passwordText = text)
	}

	private fun tryLogin() {
		if (fieldsCheck()) {
			val contentState = _state.value as? LoginState.Content ?: return
			login(contentState)
		}
	}

	private fun fieldsCheck(): Boolean {
		return (loginFieldCheck() and passwordFieldCheck())
	}

	private fun loginFieldCheck(): Boolean {
		val currentState = _state.value as? LoginState.Content ?: return false
		return if (currentState.loginType == LoginTypes.LoginWithEmail) {
			validateEmailLogin(currentState)
		} else {
			validatePhoneLogin(currentState)
		}
	}

	private fun validateEmailLogin(state: LoginState.Content): Boolean {
		val validateResult = validateEmailUseCase(state.loginText)
		return if (validateResult == EmailValidationResult.Valid) {
			_state.value = state.copy(errorLogin = LoginErrorFieldState.NoError)
			true
		} else {
			_state.value = state.copy(errorLogin = LoginErrorFieldState.EmailError(validateResult))
			false
		}
	}

	private fun validatePhoneLogin(state: LoginState.Content): Boolean {
		val validateResult = validatePhoneUseCase(state.loginText)
		return if (validateResult == PhoneValidationResult.Valid) {
			_state.value = state.copy(errorLogin = LoginErrorFieldState.NoError)
			true
		} else {
			_state.value = state.copy(errorLogin = LoginErrorFieldState.PhoneError(validateResult))
			false
		}
	}

	private fun passwordFieldCheck(): Boolean {
		val currentState = _state.value as? LoginState.Content ?: return false
		return if (currentState.passwordText.isEmpty()) {
			_state.value = currentState.copy(errorPassword = PasswordErrorFieldState.PasswordError)
			false
		} else {
			_state.value = currentState.copy(errorPassword = PasswordErrorFieldState.NoError)
			true
		}
	}

	private fun login(contentState: LoginState.Content) {
		_state.value = LoginState.Loading
		val emailEntity = LoginEntity(
			loginText = contentState.loginText,
			passwordText = contentState.passwordText,
		)
		loginByEmail(emailEntity)
	}

	private fun loginByEmail(loginEntity: LoginEntity) {
		launch(
			run = {
				loginUseCase(loginEntity)
				_state.value = LoginState.CompleteLogIn
				navigateToHome()
			}, catchError = {
				_state.value = LoginState.FailedLogIn
			}
		)
	}

	private fun navigateToHome() {
		router.navigateToHomePage()
	}
}