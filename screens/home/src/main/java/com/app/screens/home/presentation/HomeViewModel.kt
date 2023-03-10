package com.app.screens.home.presentation

import androidx.lifecycle.ViewModel

class HomeViewModel(
	private val router: HomeRouter
) : ViewModel() {

	fun navigateToAllEvents(){
		router.navigateToAllEvents()
	}

	fun navigateToYourEvents(){
		router.navigateToYourEvents()
	}
}