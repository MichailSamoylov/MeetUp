package com.app.components.validators.email

internal object EmailValidator {

	private const val validEmailRegexString =
		"^[A-Za-z\\d\\-\\_\\.]+@[A-Za-z\\d\\-\\_\\.]+\\.[A-Za-z\\d\\-\\_\\.]+\$"

	private const val AT = '@'

	private val validAllLength = 0..320

	private val validBeforeAtLength = 2..63

	fun isEmailEmpty(email: String?): Boolean = email.isNullOrEmpty()

	fun isValidEmail(email: String?): Boolean =
		email.orEmpty().matches(validEmailRegexString.toRegex())

	fun isEmailLengthAfterAtValid(email: String?): Boolean =
		if (email.orEmpty().contains(AT)) {
			email.orEmpty().substringAfter(AT).length in validBeforeAtLength
		} else {
			true
		}

	fun isEmailLengthAllValid(email: String?): Boolean = email.orEmpty().length in validAllLength
}