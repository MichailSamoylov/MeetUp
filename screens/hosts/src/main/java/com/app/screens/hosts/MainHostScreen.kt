package com.app.screens.hosts

import com.app.screens.hosts.ui.MainHostFragment
import com.app.screens.hosts.ui.MainHostScreen

fun getMainHostScreen() = MainHostScreen(
	key = MainHostFragment::class.java.simpleName,
	fragmentCreator = { MainHostFragment.newInstance() }
)
