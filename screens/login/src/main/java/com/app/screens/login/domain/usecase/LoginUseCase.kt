package com.app.screens.login.domain.usecase

import com.app.screens.login.domain.entity.LoginEntity
import com.app.screens.login.domain.repository.LoginRepository

class LoginUseCase(private val repository: LoginRepository) {

	suspend operator fun invoke(loginEntity: LoginEntity): Boolean = repository.login(loginEntity)
}