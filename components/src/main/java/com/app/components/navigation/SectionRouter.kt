package com.app.components.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class SectionRouter(private val sectionRouter: Router) {

	var backStackScreenCount: Int = 0
		private set

	fun newRootScreen(fragmentScreen: FragmentScreen) {
		sectionRouter.newRootScreen(fragmentScreen)
		backStackScreenCount = 1
	}

	fun navigateTo(fragmentScreen: FragmentScreen) {
		sectionRouter.navigateTo(fragmentScreen)
		backStackScreenCount += 1
	}

	fun exit() {
		sectionRouter.exit()
		backStackScreenCount -= 1
	}

	fun finish() {
		sectionRouter.backTo(null)
		backStackScreenCount = 0
	}
}