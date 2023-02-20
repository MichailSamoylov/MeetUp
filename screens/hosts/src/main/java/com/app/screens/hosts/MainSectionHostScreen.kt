package com.app.screens.hosts

import com.app.components.navigation.named.SectionNames
import com.app.components.navigation.wrapscreen.SectionHostScreen
import com.app.screens.hosts.ui.sections.MainSectionHostFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getMainSectionHostScreen(): FragmentScreen = SectionHostScreen(
	key = SectionNames.MAIN.name,
	fragmentCreator = { MainSectionHostFragment.newInstance() }
)