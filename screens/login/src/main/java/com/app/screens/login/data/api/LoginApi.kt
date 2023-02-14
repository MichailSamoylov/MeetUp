package com.app.screens.login.data.api

import com.app.screens.login.domain.entity.LoginEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

	@POST("/api/log-in")
	suspend fun login(@Body loginEntity: LoginEntity):Boolean
}