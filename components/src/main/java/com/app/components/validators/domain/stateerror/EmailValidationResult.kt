package com.app.components.validators.domain.stateerror

sealed class EmailValidationResult {

	object Valid : EmailValidationResult()

	object Empty : EmailValidationResult()

	object DomainLengthError : EmailValidationResult()

	object LengthError : EmailValidationResult()

	object FormatError : EmailValidationResult()
}