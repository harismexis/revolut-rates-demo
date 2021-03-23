package com.harismexis.rates.api

import com.harismexis.rates.model.RateResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApi {

    @GET("latest")
    fun getRatesSingle(
        @Query("base") baseCurrency: String,
    ): Single<RateResponse?>

}