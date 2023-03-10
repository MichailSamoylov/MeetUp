package com.app.shared.events.domain.repository

import com.app.shared.events.domain.entity.EventEntity

interface EventsRepository {

	suspend fun getEvents(id:String):List<EventEntity>
}