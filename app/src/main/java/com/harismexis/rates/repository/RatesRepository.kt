package com.harismexis.rates.repository

import com.harismexis.rates.api.RatesApi
import com.harismexis.rates.model.RateResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RatesRepository @Inject constructor() {

    companion object {
        const val BASE_URL = "https://hiring.revolut.codes/api/android/"
    }

    private val ratesApi: RatesApi

    init {
        ratesApi = createApi()
    }

    private fun createApi(): RatesApi {
        return buildRetrofit().create(RatesApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    fun getRatesSingle(
        baseCurrency: String
    ): Single<RateResponse?> {
        return ratesApi.getRatesSingle(baseCurrency)
    }

}