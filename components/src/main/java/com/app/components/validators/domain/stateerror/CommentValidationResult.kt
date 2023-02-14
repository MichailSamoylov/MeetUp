package com.app.components.validators.domain.stateerror

sealed class CommentValidationResult {

	object Valid : CommentValidationResult()

	object Empty : CommentValidationResult()

	object LengthError : CommentValidationResult()
}