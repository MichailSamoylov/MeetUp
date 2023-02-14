package com.app.meetup.navigation.routers

import com.app.screens.home.presentation.HomeRouter
import com.github.terrakok.cicerone.Router

class HomeRouterImpl(private val router:Router): HomeRouter {

	override fun navigateToSearchScreen() {

	}

	override fun navigateBack() {
		router.exit()
	}
}