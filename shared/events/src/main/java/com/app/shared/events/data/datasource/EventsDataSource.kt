package com.app.shared.events.data.datasource

import com.app.shared.events.domain.entity.EventEntity

interface EventsDataSource {

	suspend fun getEvents(id:String): List<EventEntity>
}