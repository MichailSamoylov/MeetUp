package com.app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InternetUrl {

	const val BASE_URL = "https://meet-up"
}

fun apiProvide(): Retrofit = Retrofit.Builder().apply {
	baseUrl(InternetUrl.BASE_URL)
	addConverterFactory(GsonConverterFactory.create())
}.build()