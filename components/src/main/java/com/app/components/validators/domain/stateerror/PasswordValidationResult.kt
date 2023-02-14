package com.app.components.validators.domain.stateerror

sealed class PasswordValidationResult {

	object Valid : PasswordValidationResult()

	object Empty : PasswordValidationResult()

	object LengthError : PasswordValidationResult()

	object UpperCaseError : PasswordValidationResult()

	object LowerCaseError : PasswordValidationResult()

	object DigitError : PasswordValidationResult()

	object SpaceError : PasswordValidationResult()
}