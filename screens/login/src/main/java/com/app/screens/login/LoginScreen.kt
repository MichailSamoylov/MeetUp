package com.app.screens.login

import com.app.screens.login.ui.LoginFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getLoginScreen() = FragmentScreen { LoginFragment.newInstance() }