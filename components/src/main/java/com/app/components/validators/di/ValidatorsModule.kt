package com.app.components.validators.di

import org.koin.dsl.module
import com.app.components.validators.domain.comments.ValidateCommentUseCase
import com.app.components.validators.domain.email.ValidateEmailUseCase
import com.app.components.validators.domain.password.ValidatePasswordUseCase
import com.app.components.validators.domain.phone.ValidatePhoneUseCase
import com.app.components.validators.domain.tripnumber.ValidateTripNumberUseCase

val validatorsModule = module {
	single { ValidateCommentUseCase() }
	single { ValidateEmailUseCase() }
	single { ValidatePasswordUseCase() }
	single { ValidatePhoneUseCase() }
	single { ValidateTripNumberUseCase() }
}