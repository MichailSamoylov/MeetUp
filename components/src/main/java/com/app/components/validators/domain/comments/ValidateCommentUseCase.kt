package com.app.components.validators.domain.comments

import com.app.components.validators.comments.CommentValidator
import com.app.components.validators.domain.stateerror.CommentValidationResult

class ValidateCommentUseCase {

	operator fun invoke(string: String?): CommentValidationResult =
		when {
			CommentValidator.isCommentEmpty(string)           -> CommentValidationResult.Empty
			!CommentValidator.isCommentLengthAllValid(string) -> CommentValidationResult.LengthError
			else                                              -> CommentValidationResult.Valid
		}
}