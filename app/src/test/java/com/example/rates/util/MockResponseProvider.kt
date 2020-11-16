package com.example.rates.util

import com.example.rates.model.RateResponse
import com.google.gson.Gson
import com.google.gson.JsonObject

fun getFakeResponse(jsonString: String): RateResponse {
    val json: JsonObject = Gson().fromJson(jsonString, JsonObject::class.java)
    return Gson().fromJson(json, RateResponse::class.java)
}

fun getFakeResponseEuro(): RateResponse {
    return getFakeResponse(fakeResponseEuro())
}

fun getFakeResponseAud(): RateResponse {
    return getFakeResponse(fakeResponseAud())
}