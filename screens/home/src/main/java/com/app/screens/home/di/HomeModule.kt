package com.app.screens.home.di

import com.app.screens.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val startScreenModule = module {
	viewModel {
		HomeViewModel(
			get()
		)
	}
}