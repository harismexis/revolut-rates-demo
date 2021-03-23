package com.harismexis.rates.model

import com.google.gson.annotations.SerializedName
import com.harismexis.rates.model.Rates

data class RateResponse(
    @SerializedName("baseCurrency")
    var baseCurrency: String?,
    @SerializedName("rates")
    var rates: Rates?
)