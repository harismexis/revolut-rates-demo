package com.example.rates.model

import androidx.annotation.DrawableRes
import com.example.rates.extensions.getFlagForCurrency

data class CurrencyModel(
    var currencyCode: CurrencyCode,
    var rate: Float?,
    var baseAmount: Float?
)

@DrawableRes
fun CurrencyModel?.getFlag(): Int {
    this?.let {
        return it.currencyCode.key.getFlagForCurrency()
    }
    return 0
}

fun CurrencyModel?.getAmount(): Float {
    this?.let {
        it.rate?.let { rate ->
            it.baseAmount?.let { amountToConvert ->
                return rate * amountToConvert
            }
        }
    }
    return 0.0f
}