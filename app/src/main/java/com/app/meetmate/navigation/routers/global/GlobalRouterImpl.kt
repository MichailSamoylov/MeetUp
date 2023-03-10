package com.app.meetmate.navigation.routers.global

import androidx.lifecycle.LiveData
import com.app.components.navigation.GlobalRouter
import com.app.components.navigation.SectionRouter
import com.app.components.navigation.named.SectionNames
import com.app.components.navigation.wrapscreen.FullScreen
import com.app.components.navigation.wrapscreen.SectionHostScreen
import com.app.screens.hosts.ui.MainHostScreen
import com.app.screens.login.getLoginScreen
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class GlobalRouterImpl(
	private val fullScreenRouter: Router,
	private val sectionsRouter: Router,
	private val mapOfSectionRouters: Map<SectionNames, SectionRouter>,
) : GlobalRouter {

	private val localBackStack = mutableListOf<FragmentScreen>()
	private val globalHostRouter = GlobalHostRouter(sectionsRouter, mapOfSectionRouters)
	override val currentSectionHost: LiveData<SectionNames?> = globalHostRouter.currentSectionHost

	override fun newRootScreen(screen: FragmentScreen) {
		if (localBackStack.isNotEmpty() && localBackStack.last() is MainHostScreen) {
			globalHostRouter.newRootScreen(screen)
		} else {
			fullScreenRouter.newRootScreen(screen)
			localBackStack.clear()
			localBackStack.add(screen)
		}
	}

	override fun navigateTo(screen: FragmentScreen) {
		when (screen) {
			is FullScreen        -> {
				fullScreenRouter.navigateTo(screen)
				localBackStack.add(screen)
			}

			is SectionHostScreen -> globalHostRouter.openSection(screen)

			else                 -> globalHostRouter.openScreenInSection(screen)
		}
	}

	override fun exit() {
		if (localBackStack.last() is MainHostScreen) {
			if (globalHostRouter.exit() == GlobalHostRouter.Companion.HostStatus.HOST_IS_EMPTY) {
				globalHostRouter.clearSections()
				localBackStack.removeLast()
				fullScreenRouter.finishChain()
			}
		} else {
			fullScreenRouter.exit()
			localBackStack.removeLast()
		}
	}

	override fun finish() {
		if (localBackStack.last() is MainHostScreen) {
			globalHostRouter.clearSections()
			localBackStack.clear()
			localBackStack.add(getLoginScreen())
			fullScreenRouter.newRootScreen(getLoginScreen())
		} else {
			fullScreenRouter.finishChain()
		}
	}
}