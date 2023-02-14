package com.app.components.validators.domain.tripnumber

import com.app.components.validators.domain.stateerror.TripNumberValidationResult
import com.app.components.validators.tripnumber.TripNumberValidator

class ValidateTripNumberUseCase {

	operator fun invoke(string: String?): TripNumberValidationResult =
		when {
			TripNumberValidator.isTripEmpty(string)           -> TripNumberValidationResult.Empty
			!TripNumberValidator.isTripValid(string)          -> TripNumberValidationResult.FormatError
			!TripNumberValidator.isTripLengthAllValid(string) -> TripNumberValidationResult.LengthError
			else                                              -> TripNumberValidationResult.Valid
		}
}