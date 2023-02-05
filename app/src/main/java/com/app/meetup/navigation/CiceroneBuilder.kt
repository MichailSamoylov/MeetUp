package com.app.meetup.navigation

import com.app.screens.home.getHomeScreen
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

fun buildCicerone(): Cicerone<Router> =
	Cicerone.create().apply {
		router.newRootScreen(getHomeScreen())
	}