package com.example.rates.model

import androidx.annotation.DrawableRes
import com.example.rates.extensions.getFlagForCurrency

data class RateItem(
    var currencyCode: CurrencyCode,
    var rate: Float?,
    var baseAmount: Float?
)

@DrawableRes
fun RateItem?.getFlag(): Int {
    this?.let {
        return it.currencyCode.key.getFlagForCurrency()
    }
    return 0
}

fun RateItem?.getAmount(): Float {
    this?.let {
        it.rate?.let { rate ->
            it.baseAmount?.let { amount ->
                return rate * amount
            }
        }
    }
    return 0.0f
}