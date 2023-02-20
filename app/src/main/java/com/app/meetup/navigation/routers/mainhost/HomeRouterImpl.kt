package com.app.meetup.navigation.routers.mainhost

import com.app.components.navigation.GlobalRouter
import com.app.screens.home.presentation.HomeRouter

class HomeRouterImpl(private val router: GlobalRouter) : HomeRouter {

	override fun navigateToSearchScreen() {

	}

	override fun navigateBack() {
		router.exit()
	}
}