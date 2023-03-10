package com.app.shared.accountdata.data.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.app.shared.accountdata.domain.entity.AccountData
import com.app.shared.accountdata.domain.entity.AccountLoginType

class AccountDataDataSourceImpl(private val sharedPreferences: SharedPreferences) : AccountDataDataSource {

	companion object {

		const val ACCOUNT_DATA_PREFERENCE = "ACCOUNT_DATA_PREFERENCE"
		private const val LOGIN_TYPE_KEY = "LOGIN_TYPE_KEY"
		private const val LOGIN_KEY = "LOGIN_KEY"
		private const val PASSWORD_KEY = "PASSWORD_KEY"
		private const val ID_KEY = "ID_KEY"
	}

	override fun save(accountData: AccountData) {
		sharedPreferences.edit(commit = true) {
			putString(LOGIN_TYPE_KEY, accountData.loginType.name)
			putString(LOGIN_KEY, accountData.login)
			putString(PASSWORD_KEY, accountData.password)
			putString(ID_KEY, accountData.id)
		}
	}

	override fun getSaved(): AccountData {
		val loginType = AccountLoginType.valueOf(sharedPreferences.getString(LOGIN_TYPE_KEY, null) ?: "")
		val login = sharedPreferences.getString(LOGIN_KEY, null) ?: ""
		val password = sharedPreferences.getString(PASSWORD_KEY, null) ?: ""
		val id = sharedPreferences.getString(ID_KEY, null) ?: ""

		return if (loginType == AccountLoginType.PHONE_TYPE) {
			AccountData(AccountLoginType.PHONE_TYPE, login, password, id)
		} else {
			AccountData(AccountLoginType.EMAIL_TYPE, login, password, id)
		}
	}
}