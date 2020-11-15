package com.example.rates.extensions

import android.widget.EditText
import com.example.rates.model.RateItem

fun EditText.setupRateEditText(
    item: RateItem?
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