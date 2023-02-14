package com.app.screens.login.domain.repository

import com.app.screens.login.domain.entity.LoginEntity

interface LoginRepository {

	suspend fun login(loginEntity: LoginEntity):Boolean
}