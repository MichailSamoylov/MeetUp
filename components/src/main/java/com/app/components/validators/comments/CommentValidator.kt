package com.app.components.validators.comments

internal object CommentValidator {

	private val validAllLength = 0..200

	fun isCommentEmpty(trip: String?): Boolean = trip.isNullOrEmpty()

	fun isCommentLengthAllValid(trip: String?): Boolean = trip.orEmpty().length in validAllLength
}