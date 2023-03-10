package com.app.shared.events.data.datasource

import com.app.shared.events.data.api.EventsApi
import com.app.shared.events.domain.entity.EventEntity

class EventsDataSourceImpl(private val api:EventsApi):EventsDataSource {

	override suspend fun getEvents(id: String):List<EventEntity> =
		api.getEvents(id)
}