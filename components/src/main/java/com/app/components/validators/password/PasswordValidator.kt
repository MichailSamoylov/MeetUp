package com.app.components.validators.password

object PasswordValidator {

	private const val validPasswordUpperCaseRegexString = ".*[A-Z].*"
	private const val validPasswordLowerCaseRegexString = ".*[a-z].*"
	private const val validPasswordContainsDigitRegexString = ".*[\\d].*"
	private const val validPasswordNotContainsSpacesRegexString = "^[\\S]+\$"
	private val validPasswordLength = 8..30

	fun isPasswordEmpty(password: String?): Boolean =
		password.isNullOrEmpty()

	fun isPasswordLengthValid(password: String?): Boolean =
		password?.length in validPasswordLength

	fun isPasswordUpperCaseValid(password: String?): Boolean =
		password.orEmpty().matches(validPasswordUpperCaseRegexString.toRegex())

	fun isPasswordLowerCaseValid(password: String?): Boolean =
		password.orEmpty().matches(validPasswordLowerCaseRegexString.toRegex())

	fun isPasswordContainsDigitValid(password: String?): Boolean =
		password.orEmpty().matches(validPasswordContainsDigitRegexString.toRegex())

	fun isPasswordNotContainsSpacesValid(password: String?): Boolean =
		password.orEmpty().matches(validPasswordNotContainsSpacesRegexString.toRegex())
}