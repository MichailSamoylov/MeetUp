package com.app.screens.login.ui

import com.app.components.ui.intent.IntentView

sealed class LoginIntent : IntentView {

	data class InputLoginText(
		val loginText: String
	) : LoginIntent()

	data class InputPasswordText(
		val passwordText: String
	) : LoginIntent()

	object ChangeLoginTextType : LoginIntent()

	object PressLoginButton : LoginIntent()
}
