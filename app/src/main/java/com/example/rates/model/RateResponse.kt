package com.example.rates.model

import com.google.gson.annotations.SerializedName

data class RateResponse(
    @SerializedName("baseCurrency")
    var baseCurrency: String?,
    @SerializedName("rates")
    var rates: Rates?
)