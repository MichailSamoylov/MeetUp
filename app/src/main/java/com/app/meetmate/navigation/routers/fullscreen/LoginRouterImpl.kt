package com.app.meetmate.navigation.routers.fullscreen

import com.app.components.navigation.GlobalRouter
import com.app.screens.hosts.getMainHostScreen
import com.app.screens.login.presentation.LoginRouter

class LoginRouterImpl(private val router: GlobalRouter) : LoginRouter {

	override fun navigateToHomePage() {
		router.navigateTo(getMainHostScreen())
	}

	override fun exit() {
		router.exit()
	}
}