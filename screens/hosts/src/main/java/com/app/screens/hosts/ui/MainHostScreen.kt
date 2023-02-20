package com.app.screens.hosts.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.app.components.navigation.wrapscreen.FullScreen

class MainHostScreen(
	private val key: String,
	private val fragmentCreator: () -> Fragment,
) : FullScreen(key, fragmentCreator) {

	override val screenKey: String
		get() = key

	override fun createFragment(factory: FragmentFactory): Fragment = fragmentCreator()
}