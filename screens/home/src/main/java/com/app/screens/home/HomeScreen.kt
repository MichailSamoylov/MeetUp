package com.app.screens.home

import com.app.screens.home.ui.HomeFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getHomeScreen() = FragmentScreen { HomeFragment.newInstance() }