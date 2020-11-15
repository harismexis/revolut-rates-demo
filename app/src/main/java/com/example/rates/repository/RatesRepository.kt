package com.example.rates.repository

import com.example.rates.api.RatesApi
import com.example.rates.model.RateResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RatesRepository @Inject constructor() : BaseRemoteRepository() {

    private val ratesApi: RatesApi

    init {
        ratesApi = createApi()
    }

    private fun createApi(): RatesApi {
        return buildRetrofit().create(RatesApi::class.java)
    }

    override fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRatesSingle(
        base: String
    ): Single<RateResponse?> {
        return ratesApi.getRatesSingle(base)
    }

}