package com.app.meetmate.navigation.routers.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.components.navigation.SectionRouter
import com.app.components.navigation.named.SectionNames
import com.app.components.navigation.wrapscreen.SectionHostScreen
import com.app.screens.home.getHomeScreen
import com.app.screens.hosts.getMainSectionHostScreen
import com.app.screens.map.getMapScreen
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal class GlobalHostRouter(
	private val sectionsRouter: Router,
	private val mapOfSectionRouters: Map<SectionNames, SectionRouter>,
) {

	companion object {

		const val NEXT_EXIT_MAKE_STACK_EMPTY = 1

		enum class HostStatus {
			HOST_IS_EMPTY,
			HOST_IS_NOT_EMPTY
		}
	}

	private val sectionBackStack = mutableListOf<FragmentScreen>()

	private val _currentSectionHost = MutableLiveData<SectionNames?>()
	val currentSectionHost: LiveData<SectionNames?> = _currentSectionHost

	fun newRootScreen(screen: FragmentScreen) {
		if (screen is SectionHostScreen) {
			sectionsRouter.newRootScreen(screen)
			sectionBackStack.clear()
			sectionBackStack.add(screen)
		} else {
			if (sectionBackStack.isNotEmpty()) {
				newRootScreenInSection(screen)
			} else {
				newRootScreenInMainSection(screen)
			}
		}
	}

	private fun newRootScreenInSection(screen: FragmentScreen) {
		val displayedScreenSectionName = SectionNames.valueOf(sectionBackStack.last().screenKey)
		mapOfSectionRouters[displayedScreenSectionName]?.newRootScreen(screen)
	}

	private fun newRootScreenInMainSection(screen: FragmentScreen) {
		sectionsRouter.newRootScreen(getMainSectionHostScreen())
		_currentSectionHost.value = SectionNames.MAIN
		mapOfSectionRouters[SectionNames.MAIN]?.newRootScreen(screen)
	}

	fun openSection(sectionScreen: FragmentScreen) {
		val indexSameSection = findSameSectionIndex(sectionScreen)
		addSectionScreen(sectionScreen, indexSameSection)
		val displayedSection = getDisplayedSection(sectionScreen, indexSameSection)

		val displayedScreenSectionName = SectionNames.valueOf(displayedSection.screenKey)
		if (displayedScreenSectionName == _currentSectionHost.value) {
			doOnReclickBySection(displayedScreenSectionName)
		} else {
			when (displayedSection.screenKey) {
				SectionNames.MAIN.name -> {
					sectionsRouter.navigateTo(displayedSection)
					_currentSectionHost.value = SectionNames.MAIN
				}

				SectionNames.MAP.name  -> {
					sectionsRouter.navigateTo(displayedSection)
					_currentSectionHost.value = SectionNames.MAP
				}
			}
		}
	}

	private fun doOnReclickBySection(sectionNames: SectionNames) {
		when (sectionNames) {
			SectionNames.MAIN -> mapOfSectionRouters[SectionNames.MAIN]?.newRootScreen(getHomeScreen())
			SectionNames.MAP  -> mapOfSectionRouters[SectionNames.MAP]?.newRootScreen(getMapScreen())
		}
	}

	private fun findSameSectionIndex(screen: FragmentScreen): Int? {
		var indexSameScreen: Int? = null
		sectionBackStack.forEach { section ->
			if (section.screenKey == screen.screenKey) {
				indexSameScreen = sectionBackStack.indexOf(section)
				return@forEach
			}
		}
		return indexSameScreen
	}

	private fun addSectionScreen(screen: FragmentScreen, indexSameScreen: Int?) {
		if (indexSameScreen == null) {
			sectionBackStack.add(screen)
		}
	}

	private fun getDisplayedSection(screen: FragmentScreen, indexSameScreen: Int?): FragmentScreen {
		return if (indexSameScreen == null) {
			screen
		} else {
			return sectionBackStack[indexSameScreen]
		}
	}

	fun openScreenInSection(screen: FragmentScreen) {
		mapOfSectionRouters[_currentSectionHost.value]?.navigateTo(screen)
	}

	fun exit(): HostStatus {
		val sectionSize = mapOfSectionRouters[currentSectionHost.value]?.backStackScreenCount ?: 0
		if (currentSectionHost.value == SectionNames.MAIN) {
			if (exitFromMainSection(sectionSize) == HostStatus.HOST_IS_EMPTY) {
				return HostStatus.HOST_IS_EMPTY
			}
		} else {
			exitFromSection(sectionSize)
		}
		return HostStatus.HOST_IS_NOT_EMPTY
	}

	private fun exitFromMainSection(sectionSize: Int): HostStatus =
		when {
			sectionBackStack.size == NEXT_EXIT_MAKE_STACK_EMPTY && sectionSize == NEXT_EXIT_MAKE_STACK_EMPTY -> {
				clearSections()
				HostStatus.HOST_IS_EMPTY
			}

			sectionSize > NEXT_EXIT_MAKE_STACK_EMPTY                                                         -> {
				mapOfSectionRouters[_currentSectionHost.value]?.exit()
				HostStatus.HOST_IS_NOT_EMPTY
			}

			else                                                                                             -> {
				sectionsRouter.replaceScreen(sectionBackStack.last())
				_currentSectionHost.value = SectionNames.valueOf(sectionBackStack.last().screenKey)
				HostStatus.HOST_IS_NOT_EMPTY
			}
		}

	private fun exitFromSection(sectionSize: Int) {
		if (sectionSize > NEXT_EXIT_MAKE_STACK_EMPTY) {
			mapOfSectionRouters[_currentSectionHost.value]?.exit()
		} else {
			sectionBackStack.removeLast()
			sectionsRouter.replaceScreen(sectionBackStack.last())
			_currentSectionHost.value = SectionNames.valueOf(sectionBackStack.last().screenKey)
		}
	}

	fun clearSections() {
		mapOfSectionRouters.forEach { (_, router) ->
			router.finish()
		}
		_currentSectionHost.value = null
		sectionBackStack.clear()
	}
}