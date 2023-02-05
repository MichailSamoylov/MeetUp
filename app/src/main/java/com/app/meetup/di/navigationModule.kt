package com.app.meetup.di

import com.app.meetup.navigation.HomeRouterImpl
import com.app.screens.home.presentation.HomeRouter
import org.koin.dsl.module

val navigationModule = module {
	factory<HomeRouter> { HomeRouterImpl(get()) }
}