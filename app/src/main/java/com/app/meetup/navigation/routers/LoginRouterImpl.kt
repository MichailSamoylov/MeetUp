package com.app.meetup.navigation.routers

import com.app.screens.home.getHomeScreen
import com.app.screens.login.presentation.LoginRouter
import com.github.terrakok.cicerone.Router

class LoginRouterImpl(private val router: Router) : LoginRouter {

	override fun navigateToHomePage() {
		router.navigateTo(getHomeScreen())
	}

	override fun exit() {
		router.exit()
	}
}