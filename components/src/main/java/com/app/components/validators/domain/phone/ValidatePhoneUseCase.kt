package com.app.components.validators.domain.phone

import com.app.components.validators.domain.stateerror.PhoneValidationResult
import com.app.components.validators.phone.PhoneValidator

class ValidatePhoneUseCase {

	operator fun invoke(string: String?): PhoneValidationResult =
		when {
			PhoneValidator.isPhoneEmpty(string)        -> PhoneValidationResult.Empty
			!PhoneValidator.isPhoneLengthValid(string) -> PhoneValidationResult.LengthError
			!PhoneValidator.isValidPhone(string)       -> PhoneValidationResult.FormatError
			else                                       -> PhoneValidationResult.Valid
		}
}