package com.app.components.validators.domain.stateerror

sealed class PhoneValidationResult {

	object Valid : PhoneValidationResult()

	object Empty : PhoneValidationResult()

	object LengthError : PhoneValidationResult()

	object FormatError : PhoneValidationResult()
}