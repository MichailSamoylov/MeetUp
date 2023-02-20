package com.app.components.navigation.wrapscreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class SectionHostScreen(
	private val key: String,
	private val fragmentCreator: () -> Fragment,
) : FragmentScreen {

	override val screenKey: String
		get() = key

	override fun createFragment(factory: FragmentFactory): Fragment = fragmentCreator()

}