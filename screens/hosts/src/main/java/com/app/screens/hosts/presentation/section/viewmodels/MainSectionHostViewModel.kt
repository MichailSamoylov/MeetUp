package com.app.screens.hosts.presentation.section.viewmodels

import androidx.lifecycle.ViewModel
import com.app.screens.hosts.presentation.section.routers.MainSectionHostRouter

class MainSectionHostViewModel(
	private val router: MainSectionHostRouter
) : ViewModel() {

	fun initRouter() {
		router.init()
	}
}