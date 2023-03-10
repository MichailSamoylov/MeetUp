package com.app.shared.accountdata.domain.repository

import com.app.shared.accountdata.domain.entity.AccountData

interface AccountDataRepository {

	fun saveAccountData(accountData: AccountData)

	fun getSavedAccountData(): AccountData
}