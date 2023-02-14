package com.app.components.validators.domain.stateerror

sealed class TripNumberValidationResult {

	object Valid : TripNumberValidationResult()

	object Empty : TripNumberValidationResult()

	object LengthError : TripNumberValidationResult()

	object FormatError : TripNumberValidationResult()
}