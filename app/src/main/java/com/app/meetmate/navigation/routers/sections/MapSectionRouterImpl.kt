package com.app.meetmate.navigation.routers.sections

import com.app.components.navigation.GlobalRouter
import com.app.screens.hosts.presentation.section.routers.MapSectionHostRouter
import com.app.screens.map.getMapScreen

class MapSectionRouterImpl(private val mainRouter: GlobalRouter) : MapSectionHostRouter {

	private var isInit: Boolean = false

	override fun init() {
		if (!isInit) {
			isInit = true
			mainRouter.navigateTo(getMapScreen())
		}
	}
}