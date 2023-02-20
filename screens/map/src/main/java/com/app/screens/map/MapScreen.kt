package com.app.screens.map

import com.app.screens.map.ui.MapFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getMapScreen() = FragmentScreen { MapFragment.newInstance() }