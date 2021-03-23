package com.harismexis.rates.model

import androidx.annotation.DrawableRes
import com.harismexis.rates.extensions.getFlagForCurrency

data class CurrencyModel(
    var currencyCode: Currency,
    var rate: Float?,
    var baseAmount: Float?,
    var firstResponderInput: String?
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

fun CurrencyModel?.amountAsString(): String {
    this?.let {
        return String.format("%.2f", it.getAmount())
    }
    return ""
}