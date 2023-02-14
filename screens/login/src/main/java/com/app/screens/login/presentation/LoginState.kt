package com.app.screens.login.presentation

import com.app.components.presentation.state.StateView
import com.app.components.validators.domain.stateerror.EmailValidationResult
import com.app.components.validators.domain.stateerror.PhoneValidationResult

sealed class LoginState : StateView {

	object Initial : LoginState()

	object Loading : LoginState()

	data class Content(
		val loginText: String,
		val passwordText: String,
		val loginType: LoginTypes,
		val errorLogin: LoginErrorFieldState,
		val errorPassword: PasswordErrorFieldState,
		val previousLoginType: LoginTypes
	) : LoginState()

	object CompleteLogIn : LoginState()

	object FailedLogIn : LoginState()
}

sealed class LoginErrorFieldState {

	data class EmailError(
		val errorType: EmailValidationResult
	) : LoginErrorFieldState()

	data class PhoneError(
		val errorType: PhoneValidationResult
	) : LoginErrorFieldState()

	object NoError : LoginErrorFieldState()
}

sealed class PasswordErrorFieldState {

	object PasswordError : PasswordErrorFieldState()

	object NoError : PasswordErrorFieldState()
}

sealed class LoginTypes {

	object LoginWithPhone : LoginTypes()

	object LoginWithEmail : LoginTypes()
}