package com.app.meetup.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.meetup.R
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

	private val navigateHolder: NavigatorHolder by inject()
	private val navigator = AppNavigator(this, R.id.container)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	override fun onResume() {
		super.onResume()
		navigateHolder.setNavigator(navigator)
	}

	override fun onPause() {
		super.onPause()
		navigateHolder.removeNavigator()
	}
}