package com.example.rates.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

open class BaseRemoteRepository @Inject constructor() {

    companion object {
        const val BASE_URL = "https://hiring.revolut.codes/api/android/"
    }

    open fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    open fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}