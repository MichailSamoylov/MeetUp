package com.app.meetmate

import android.app.Application
import com.app.components.validators.di.validatorsModule
import com.app.meetmate.di.ciceroneModule
import com.app.meetmate.di.fullScreenRoutersModule
import com.app.meetmate.di.navigationModule
import com.app.meetmate.di.sectionRoutersModule
import com.app.screens.home.di.homeModule
import com.app.screens.hosts.di.mainHostModule
import com.app.screens.login.di.loginModule
import com.app.shared.accountdata.di.accountDataModule
import com.app.shared.events.di.eventsModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		MapKitFactory.setApiKey("47d8d493-2fab-4f08-bff6-9a780c331eb1")

		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)

			modules(
				ciceroneModule,
				fullScreenRoutersModule,
				sectionRoutersModule,
				mainHostModule,
				navigationModule,
				loginModule,
				homeModule,
				validatorsModule,
				eventsModule,
				accountDataModule,
			)
		}
	}
}