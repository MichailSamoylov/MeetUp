package com.app.shared.accountdata.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import com.app.shared.accountdata.data.datasource.AccountDataDataSource
import com.app.shared.accountdata.data.datasource.AccountDataDataSourceImpl
import com.app.shared.accountdata.data.datasource.AccountDataDataSourceImpl.Companion.ACCOUNT_DATA_PREFERENCE
import ru.cft.perscab.shared.accountdata.data.repository.AccountDataRepositoryImpl
import com.app.shared.accountdata.domain.repository.AccountDataRepository
import com.app.shared.accountdata.domain.usecase.GetSavedAccountDataUseCase
import com.app.shared.accountdata.domain.usecase.SaveAccountDataUseCase

val accountDataModule = module {
	factory<AccountDataDataSource> {
		AccountDataDataSourceImpl(
			sharedPreferences = androidContext().getSharedPreferences(ACCOUNT_DATA_PREFERENCE, Context.MODE_PRIVATE)
		)
	}
	factory<AccountDataRepository> { AccountDataRepositoryImpl(get()) }
	factory { SaveAccountDataUseCase(get()) }
	factory { GetSavedAccountDataUseCase(get()) }
}