package com.app.meetmate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.components.navigation.GlobalRouter
import com.app.components.navigation.named.RouterNames.FULL_SCREEN
import com.app.meetmate.R
import com.app.screens.hosts.getMainHostScreen
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

	private val globalRouter: GlobalRouter by inject()
	private val fullScreenNavigateHolder: NavigatorHolder by inject(named(FULL_SCREEN))
	private val navigator = AppNavigator(this, R.id.container)

	override fun onCreate(savedInstanceState: Bundle?) {
		setContentView(R.layout.activity_main)
		super.onCreate(savedInstanceState)
//		globalRouter.newRootScreen(getLoginScreen())
		globalRouter.newRootScreen(getMainHostScreen())
	}

	override fun onResume() {
		super.onResume()
		fullScreenNavigateHolder.setNavigator(navigator)
	}

	override fun onPause() {
		super.onPause()
		fullScreenNavigateHolder.removeNavigator()
	}
}