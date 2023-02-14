package com.app.components.validators.domain.password

import com.app.components.validators.domain.stateerror.PasswordValidationResult
import com.app.components.validators.password.PasswordValidator

class ValidatePasswordUseCase {

	private val passwordErrorList = mutableListOf<PasswordValidationResult>()

	operator fun invoke(string: String?): List<PasswordValidationResult> {
		passwordErrorList.clear()
		if (PasswordValidator.isPasswordEmpty(string)) {
			passwordErrorList.add(PasswordValidationResult.Empty)
			return passwordErrorList
		}
		if (!PasswordValidator.isPasswordUpperCaseValid(string)) {
			passwordErrorList.add(PasswordValidationResult.UpperCaseError)
		}
		if (!PasswordValidator.isPasswordLowerCaseValid(string)) {
			passwordErrorList.add(PasswordValidationResult.LowerCaseError)
		}
		if (!PasswordValidator.isPasswordContainsDigitValid(string)) {
			passwordErrorList.add(PasswordValidationResult.DigitError)
		}
		if (!PasswordValidator.isPasswordLengthValid(string)) {
			passwordErrorList.add(PasswordValidationResult.LengthError)
		}
		if (!PasswordValidator.isPasswordNotContainsSpacesValid(string)) {
			passwordErrorList.add(PasswordValidationResult.SpaceError)
		}
		if (passwordErrorList.isEmpty()) passwordErrorList.add(PasswordValidationResult.Valid)

		return passwordErrorList
	}
}