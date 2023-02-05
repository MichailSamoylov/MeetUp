package com.app.meetup

import android.app.Application
import com.app.meetup.di.ciceroneModule
import com.app.meetup.di.navigationModule
import com.app.screens.home.di.startScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)

			modules(
				ciceroneModule,
				navigationModule,
				startScreenModule
			)
		}
	}
}