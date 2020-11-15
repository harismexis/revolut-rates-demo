package com.example.rates.extensions

import android.widget.EditText
import com.example.rates.model.CurrencyModel

fun EditText.setupRateEditText(
    item: CurrencyModel?
) {
    item?.let {
        item.rate?.let { rate ->
            item.baseAmount?.let { amount ->
                var msg = (rate * amount).toString()
                this.setText(msg)
            }
        }
    }

}