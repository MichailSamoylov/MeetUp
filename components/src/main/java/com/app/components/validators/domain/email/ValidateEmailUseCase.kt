package com.app.components.validators.domain.email

import com.app.components.validators.domain.stateerror.EmailValidationResult
import com.app.components.validators.email.EmailValidator

class ValidateEmailUseCase {

	operator fun invoke(string: String?): EmailValidationResult =
		when {
			EmailValidator.isEmailEmpty(string)               -> EmailValidationResult.Empty
			!EmailValidator.isEmailLengthAllValid(string)     -> EmailValidationResult.LengthError
			!EmailValidator.isValidEmail(string)              -> EmailValidationResult.FormatError
			!EmailValidator.isEmailLengthAfterAtValid(string) -> EmailValidationResult.DomainLengthError
			else                                              -> EmailValidationResult.Valid
		}
}