package com.app.shared.events.domain.usecase

import com.app.shared.accountdata.domain.usecase.GetSavedAccountDataUseCase
import com.app.shared.events.domain.entity.EventEntity
import com.app.shared.events.domain.repository.EventsRepository

class GetMyEventsUseCase(private val repository: EventsRepository, private val getAccountDataUseCase: GetSavedAccountDataUseCase) {

	suspend operator fun invoke(): List<EventEntity> {
		val id = getAccountDataUseCase().id
		return repository.getEvents(id)
	}
}