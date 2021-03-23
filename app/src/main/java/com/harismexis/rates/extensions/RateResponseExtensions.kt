package com.harismexis.rates.extensions

import com.harismexis.rates.model.CurrencyModel
import com.harismexis.rates.model.RateResponse

fun RateResponse?.convertToUiModels(
    baseCurrencyKey: String,
    baseAmount: Float?,
    firstResponderInput: String?
): List<CurrencyModel> {
    val uiModels = ArrayList<CurrencyModel>()
    lateinit var firstResponder: CurrencyModel
    this?.let { it ->
        it.rates?.let { rates ->
            for (entry in rates.currencyMap()) {
                val uiModel = CurrencyModel(
                    entry.key,
                    entry.value,
                    baseAmount,
                    firstResponderInput
                )
                if (entry.key.key == baseCurrencyKey) {
                    firstResponder = uiModel
                } else {
                    uiModels.add(uiModel)
                }
            }
            uiModels.add(0, firstResponder)
        }
    }
    return uiModels
}