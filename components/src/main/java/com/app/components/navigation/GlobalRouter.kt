package com.app.components.navigation

import androidx.lifecycle.LiveData
import com.app.components.navigation.named.SectionNames
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface GlobalRouter {

	val currentSectionHost: LiveData<SectionNames?>

	fun newRootScreen(screen: FragmentScreen)

	fun navigateTo(screen: FragmentScreen)

	fun exit()

	fun finish()
}