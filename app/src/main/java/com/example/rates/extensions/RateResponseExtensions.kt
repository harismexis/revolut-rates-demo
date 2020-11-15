package com.example.rates.extensions

import com.example.rates.model.RateItem
import com.example.rates.model.RateResponse

fun RateResponse?.convertToUiModels(baseAmount: Float?): List<RateItem> {
    val uiModels = ArrayList<RateItem>()
    lateinit var firstResponder: RateItem
    this?.let { it ->
        it.rates?.let { rates ->
            for (entry in rates.currencyMap()) {
                val uiModel = RateItem(
                    entry.key,
                    entry.value,
                    baseAmount
                )
                if (entry.value == null) {
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