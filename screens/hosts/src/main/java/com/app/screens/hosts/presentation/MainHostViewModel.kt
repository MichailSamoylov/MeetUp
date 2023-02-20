package com.app.screens.hosts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.components.navigation.named.SectionNames
import com.app.screens.hosts.presentation.router.MainHostRouter

class MainHostViewModel(
	private val mainHostRouter: MainHostRouter,
) : ViewModel() {

	private var stateProcessing = false
	val currentHost: LiveData<SectionNames?> = mainHostRouter.currentSectionHost

	fun init() {
		if (currentHost.value == null)
			mainHostRouter.navigateToMainMenu()
	}

	fun stateProcessingStart() {
		stateProcessing = true
	}

	fun stateProcessingEnd() {
		stateProcessing = false
	}

	fun navigateToMainSection() {
		if (!stateProcessing) {
			mainHostRouter.navigateToMainMenu()
		}
	}

	fun navigateToMapSection() {
		if (!stateProcessing) {
			mainHostRouter.navigateToMapMenu()
		}
	}

	fun exit() {
		mainHostRouter.navigateBack()
	}
}