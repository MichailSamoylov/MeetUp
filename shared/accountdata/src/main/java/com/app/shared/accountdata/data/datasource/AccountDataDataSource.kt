package com.app.shared.accountdata.data.datasource

import com.app.shared.accountdata.domain.entity.AccountData

interface AccountDataDataSource {

	fun save(accountData: AccountData)

	fun getSaved(): AccountData
}