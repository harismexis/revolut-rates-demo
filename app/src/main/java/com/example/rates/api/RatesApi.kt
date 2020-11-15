package com.example.rates.api

import com.example.rates.model.RateResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApi {

    @GET("latest")
    fun getRatesSingle(
        @Query("base") page: String,
    ): Single<RateResponse?>

}