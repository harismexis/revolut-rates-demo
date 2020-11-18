package com.example.rates.extensions

import android.widget.TextView
import com.example.rates.R

fun TextView.setTextOrUnknown(accountName: String?) {
    if (!accountName.isNullOrBlank()) {
        this.text = accountName
    } else {
        this.text = this.context.getString(R.string.unknown)
    }
}