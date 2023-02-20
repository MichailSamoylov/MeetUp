package com.app.meetup.di

import com.app.components.navigation.named.RouterNames.FULL_SCREEN
import com.app.components.navigation.named.RouterNames.MAIN_HOST
import com.app.components.navigation.named.RouterNames.MAIN_SECTION
import com.app.components.navigation.named.RouterNames.MAP_SECTION
import com.app.meetup.navigation.buildCicerone
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ciceroneModule = module {
	single(named(FULL_SCREEN)) { buildCicerone() }
	single(named(FULL_SCREEN)) { get<Cicerone<Router>>(named(FULL_SCREEN)).router }
	single(named(FULL_SCREEN)) { get<Cicerone<Router>>(named(FULL_SCREEN)).getNavigatorHolder() }

	single(named(MAIN_HOST)) { buildCicerone() }
	single(named(MAIN_HOST)) { get<Cicerone<Router>>(named(MAIN_HOST)).router }
	single(named(MAIN_HOST)) { get<Cicerone<Router>>(named(MAIN_HOST)).getNavigatorHolder() }

	single(named(MAIN_SECTION)) { buildCicerone() }
	single(named(MAIN_SECTION)) { get<Cicerone<Router>>(named(MAIN_SECTION)).router }
	single(named(MAIN_SECTION)) { get<Cicerone<Router>>(named(MAIN_SECTION)).getNavigatorHolder() }

	single(named(MAP_SECTION)) { buildCicerone() }
	single(named(MAP_SECTION)) { get<Cicerone<Router>>(named(MAP_SECTION)).router }
	single(named(MAP_SECTION)) { get<Cicerone<Router>>(named(MAP_SECTION)).getNavigatorHolder() }
}