package com.app.components.validators.phone

object PhoneValidator {

	private const val validPhoneRegexString = "^[\\d\\-\\+\\s\\(\\)]+\$"

	private const val validLength = 18

	fun isPhoneEmpty(phone: String?): Boolean = phone.isNullOrEmpty()

	fun isValidPhone(phone: String?): Boolean =
		phone.orEmpty().matches(validPhoneRegexString.toRegex())

	fun isPhoneLengthValid(phone: String?): Boolean = phone.orEmpty().length == validLength
}