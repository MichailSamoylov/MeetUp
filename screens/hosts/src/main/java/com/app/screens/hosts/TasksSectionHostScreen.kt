package com.app.screens.hosts

import com.app.components.navigation.named.SectionNames
import com.app.components.navigation.wrapscreen.SectionHostScreen
import com.app.screens.hosts.ui.sections.MapSectionHostFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getMapSectionHostScreen(): FragmentScreen = SectionHostScreen(
	key = SectionNames.MAP.name,
	fragmentCreator = { MapSectionHostFragment.newInstance() }
)