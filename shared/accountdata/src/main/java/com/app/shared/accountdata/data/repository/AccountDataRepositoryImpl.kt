package ru.cft.perscab.shared.accountdata.data.repository

import com.app.shared.accountdata.data.datasource.AccountDataDataSource
import com.app.shared.accountdata.domain.entity.AccountData
import com.app.shared.accountdata.domain.repository.AccountDataRepository

class AccountDataRepositoryImpl(private val dataSource: AccountDataDataSource) : AccountDataRepository {

	override fun saveAccountData(accountData: AccountData) {
		dataSource.save(accountData)
	}

	override fun getSavedAccountData(): AccountData = dataSource.getSaved()
}