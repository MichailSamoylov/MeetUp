package com.app.shared.accountdata.domain.entity

import java.io.Serializable

data class AccountData(
	val loginType: AccountLoginType,
	val login: String,
	val password: String,
	val id: String,
) : Serializable

enum class AccountLoginType(name: String) {
	PHONE_TYPE("PHONE_TYPE"),
	EMAIL_TYPE("EMAIL_TYPE")
}