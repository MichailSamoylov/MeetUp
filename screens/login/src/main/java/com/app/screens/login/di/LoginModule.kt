package com.app.screens.login.di

import com.app.network.apiProvide
import com.app.screens.login.data.api.LoginApi
import com.app.screens.login.data.datasource.LoginDataSource
import com.app.screens.login.data.datasource.LoginDataSourceImpl
import com.app.screens.login.data.repository.LoginRepositoryImpl
import com.app.screens.login.domain.repository.LoginRepository
import com.app.screens.login.domain.usecase.LoginUseCase
import com.app.screens.login.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
	factory<LoginApi> { apiProvide().create(LoginApi::class.java) }
	factory<LoginDataSource> { LoginDataSourceImpl(get()) }
	factory<LoginRepository> { LoginRepositoryImpl(get()) }
	factory { LoginUseCase(get()) }
	viewModel {
		LoginViewModel(
			router = get(),
			loginUseCase = get(),
			validateEmailUseCase = get(),
			validatePhoneUseCase = get()
		)
	}
}