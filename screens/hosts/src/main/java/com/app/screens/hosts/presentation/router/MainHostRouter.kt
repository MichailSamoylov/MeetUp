package com.app.screens.hosts.presentation.router

import androidx.lifecycle.LiveData
import com.app.components.navigation.named.SectionNames

interface MainHostRouter {

	val currentSectionHost: LiveData<SectionNames?>

	fun navigateToMainMenu()

	fun navigateToMapMenu()

	fun navigateBack()
}