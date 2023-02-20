package com.app.screens.hosts.di

import com.app.screens.hosts.presentation.MainHostViewModel
import com.app.screens.hosts.presentation.section.viewmodels.MainSectionHostViewModel
import com.app.screens.hosts.presentation.section.viewmodels.MapSectionHostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainHostModule = module {
	viewModel {
		MainHostViewModel(
			mainHostRouter = get()
		)
	}
	viewModel {
		MainSectionHostViewModel(
			router = get()
		)
	}
	viewModel {
		MapSectionHostViewModel(
			router = get()
		)
	}
}