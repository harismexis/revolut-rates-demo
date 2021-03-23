package com.harismexis.rates.util

import com.harismexis.rates.model.RateResponse
import com.google.gson.Gson
import com.google.gson.JsonObject

private fun convertStringToResponse(jsonString: String): RateResponse {
    val json: JsonObject = Gson().fromJson(jsonString, JsonObject::class.java)
    return Gson().fromJson(json, RateResponse::class.java)
}

fun getFakeResponseEuro(): RateResponse {
    return convertStringToResponse(fakeResponseEuro())
}

fun getFakeResponseAud(): RateResponse {
    return convertStringToResponse(fakeResponseAud())
}