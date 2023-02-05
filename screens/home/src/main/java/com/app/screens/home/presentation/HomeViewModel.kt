package com.app.screens.home.presentation

import androidx.lifecycle.ViewModel

class HomeViewModel(
	private val router: HomeRouter
) : ViewModel() {

	fun navigateToSearchScreen(){
		router.navigateToSearchScreen()
	}

	fun navigateBack(){
		router.navigateBack()
	}
}