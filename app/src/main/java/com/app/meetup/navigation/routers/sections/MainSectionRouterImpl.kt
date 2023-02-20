package com.app.meetup.navigation.routers.sections

import com.app.components.navigation.GlobalRouter
import com.app.screens.home.getHomeScreen
import com.app.screens.hosts.presentation.section.routers.MainSectionHostRouter

class MainSectionRouterImpl(private val mainRouter: GlobalRouter) : MainSectionHostRouter {

	private var isInit: Boolean = false

	override fun init() {
		if (!isInit) {
			isInit = true
			mainRouter.navigateTo(getHomeScreen())
		}
	}
}