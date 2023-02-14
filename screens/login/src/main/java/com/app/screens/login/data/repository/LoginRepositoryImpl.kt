package com.app.screens.login.data.repository

import com.app.screens.login.data.datasource.LoginDataSource
import com.app.screens.login.domain.entity.LoginEntity
import com.app.screens.login.domain.repository.LoginRepository

class LoginRepositoryImpl(private val dataSource: LoginDataSource) : LoginRepository {

	override suspend fun login(loginEntity: LoginEntity): Boolean = dataSource.login(loginEntity)

}