package com.app.meetup.di

import com.app.components.navigation.GlobalRouter
import com.app.components.navigation.SectionRouter
import com.app.components.navigation.named.RouterNames.FULL_SCREEN
import com.app.components.navigation.named.RouterNames.MAIN_HOST
import com.app.components.navigation.named.RouterNames.MAIN_SECTION
import com.app.components.navigation.named.RouterNames.MAP_SECTION
import com.app.components.navigation.named.SectionNames
import com.app.meetup.navigation.routers.fullscreen.LoginRouterImpl
import com.app.meetup.navigation.routers.fullscreen.MainHostRouterImpl
import com.app.meetup.navigation.routers.global.GlobalRouterImpl
import com.app.meetup.navigation.routers.mainhost.HomeRouterImpl
import com.app.meetup.navigation.routers.sections.MainSectionRouterImpl
import com.app.meetup.navigation.routers.sections.MapSectionRouterImpl
import com.app.screens.home.presentation.HomeRouter
import com.app.screens.hosts.presentation.router.MainHostRouter
import com.app.screens.hosts.presentation.section.routers.MainSectionHostRouter
import com.app.screens.hosts.presentation.section.routers.MapSectionHostRouter
import com.app.screens.login.presentation.LoginRouter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val fullScreenRoutersModule = module {
	factory<LoginRouter> { LoginRouterImpl(router = get()) }
	factory<MainHostRouter> { MainHostRouterImpl(router = get()) }
	single<GlobalRouter> {
		GlobalRouterImpl(
			fullScreenRouter = get(named(FULL_SCREEN)),
			sectionsRouter = get(named(MAIN_HOST)),
			mapOfSectionRouters = mapOf(
				SectionNames.MAIN to SectionRouter(get(named(MAIN_SECTION))),
				SectionNames.MAP to SectionRouter(get(named(MAP_SECTION))),
			)
		)
	}
}

val sectionRoutersModule = module {
	factory<MainSectionHostRouter> { MainSectionRouterImpl(get()) }
	factory<MapSectionHostRouter> { MapSectionRouterImpl(get()) }
}

val navigationModule = module {
	factory<HomeRouter> { HomeRouterImpl(get()) }
	factory<LoginRouter> { LoginRouterImpl(get()) }
}