package com.app.screens.hosts.presentation.section.viewmodels

import androidx.lifecycle.ViewModel
import com.app.screens.hosts.presentation.section.routers.MapSectionHostRouter

class MapSectionHostViewModel(
	private val router: MapSectionHostRouter
) : ViewModel() {

	fun initRouter() {
		router.init()
	}
}