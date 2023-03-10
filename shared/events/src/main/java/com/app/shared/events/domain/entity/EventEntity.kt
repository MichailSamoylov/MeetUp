package com.app.shared.events.domain.entity

data class EventEntity(
	val name: String,
	val timeToStart: Long,
	val nickNameManager: String,
	val countSubscribers: Int,
)