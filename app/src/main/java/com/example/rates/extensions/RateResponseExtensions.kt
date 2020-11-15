package com.example.rates.extensions

import com.example.rates.model.CurrencyModel
import com.example.rates.model.RateResponse

fun RateResponse?.convertToUiModels(
    baseCurrencyKey: String,
    baseAmount: Float?,
    ): List<CurrencyModel> {
    val uiModels = ArrayList<CurrencyModel>()
    lateinit var firstResponder: CurrencyModel
    this?.let { it ->
        it.rates?.let { rates ->
            for (entry in rates.currencyMap()) {
                val uiModel = CurrencyModel(
                    entry.key,
                    entry.value,
                    baseAmount
                )
                if (entry.value == null && entry.key.key == baseCurrencyKey) {
                    firstResponder = uiModel
                } else {
                    uiModels.add(uiModel)
                }
            }
            uiModels.sortedWith((compareBy { item -> item.currencyCode.key }))
            uiModels.add(0, firstResponder)
        }
    }
    return uiModels
}