package com.app.meetup.navigation.routers.fullscreen

import androidx.lifecycle.LiveData
import com.app.components.navigation.GlobalRouter
import com.app.components.navigation.named.SectionNames
import com.app.screens.hosts.getMainSectionHostScreen
import com.app.screens.hosts.getMapSectionHostScreen
import com.app.screens.hosts.presentation.router.MainHostRouter

class MainHostRouterImpl(
	private val router: GlobalRouter
) : MainHostRouter {

	override val currentSectionHost: LiveData<SectionNames?>
		get() = router.currentSectionHost

	override fun navigateToMainMenu() {
		router.navigateTo(getMainSectionHostScreen())
	}

	override fun navigateToMapMenu() {
		router.navigateTo(getMapSectionHostScreen())
	}

	override fun navigateBack() {
		router.exit()
	}
}

