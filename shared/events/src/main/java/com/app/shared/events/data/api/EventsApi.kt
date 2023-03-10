package com.app.shared.events.data.api

import com.app.shared.events.domain.entity.EventEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsApi {

	@GET("/api/meet-mate/events/{id}")
	suspend fun getEvents(@Path("id") id: String): List<EventEntity>
}