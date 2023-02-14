package com.app.meetup.di

import com.app.meetup.navigation.routers.HomeRouterImpl
import com.app.meetup.navigation.routers.LoginRouterImpl
import com.app.screens.home.presentation.HomeRouter
import com.app.screens.login.presentation.LoginRouter
import org.koin.dsl.module

val navigationModule = module {
	factory<HomeRouter> { HomeRouterImpl(get()) }
	factory<LoginRouter> { LoginRouterImpl(get()) }
}