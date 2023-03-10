package com.app.shared.events.di

import com.app.network.apiProvide
import com.app.shared.events.data.api.EventsApi
import com.app.shared.events.data.datasource.EventsDataSource
import com.app.shared.events.data.datasource.EventsDataSourceImpl
import com.app.shared.events.data.repository.EventsRepositoryImpl
import com.app.shared.events.domain.repository.EventsRepository
import com.app.shared.events.domain.usecase.GetMyEventsUseCase
import org.koin.dsl.module

val eventsModule = module {
	factory<EventsApi> { apiProvide().create(EventsApi::class.java) }
	factory<EventsDataSource> { EventsDataSourceImpl(get()) }
	factory<EventsRepository> { EventsRepositoryImpl(get()) }
	factory { GetMyEventsUseCase(get(), get()) }
}