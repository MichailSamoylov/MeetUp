package com.app.components.validators.tripnumber

internal object TripNumberValidator {

	private const val validTripNumberRegex = "[0-9]+"

	private val validAllLength = 0..15

	fun isTripEmpty(trip: String?): Boolean = trip.isNullOrEmpty()

	fun isTripValid(trip: String?): Boolean = trip.orEmpty().matches(validTripNumberRegex.toRegex())

	fun isTripLengthAllValid(trip: String?): Boolean = trip.orEmpty().length in validAllLength
}