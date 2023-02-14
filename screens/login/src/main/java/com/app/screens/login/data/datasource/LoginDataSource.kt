package com.app.screens.login.data.datasource

import com.app.screens.login.domain.entity.LoginEntity

interface LoginDataSource {

	suspend fun login(entity: LoginEntity):Boolean
}