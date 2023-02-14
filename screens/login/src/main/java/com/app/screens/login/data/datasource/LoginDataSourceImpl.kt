package com.app.screens.login.data.datasource

import com.app.screens.login.data.api.LoginApi
import com.app.screens.login.domain.entity.LoginEntity

class LoginDataSourceImpl(private val api: LoginApi) : LoginDataSource {

	override suspend fun login(entity: LoginEntity):Boolean = api.login(entity)
}