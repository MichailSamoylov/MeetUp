package com.app.shared.accountdata.domain.usecase

import com.app.shared.accountdata.domain.entity.AccountData
import com.app.shared.accountdata.domain.repository.AccountDataRepository

class SaveAccountDataUseCase(private val repository: AccountDataRepository) {

	operator fun invoke(accountData: AccountData) {
		repository.saveAccountData(accountData)
	}
}