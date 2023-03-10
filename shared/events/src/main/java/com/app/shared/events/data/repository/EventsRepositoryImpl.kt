package com.app.shared.events.data.repository

import com.app.shared.events.data.datasource.EventsDataSource
import com.app.shared.events.domain.entity.EventEntity
import com.app.shared.events.domain.repository.EventsRepository

class EventsRepositoryImpl(private val dataSource: EventsDataSource):EventsRepository {

	override suspend fun getEvents(id: String): List<EventEntity> =
		dataSource.getEvents(id)
}