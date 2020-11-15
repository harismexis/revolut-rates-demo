package com.example.rates.extensions

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.example.rates.R
import com.example.rates.model.CurrencyCode

fun TextView.setTextOrUnknown(accountName: String?) {
    if (!accountName.isNullOrBlank()) {
        this.text = accountName
    } else {
        this.text = this.context.getString(R.string.unknown)
    }
}

fun ImageView.setCurrencyIcon(currencyCode: String?) {
    this.setImageResource(getFlagForCurrency(currencyCode))
}

@DrawableRes
fun getFlagForCurrency(currencyCode: String?): Int {
    return when (currencyCode) {
        CurrencyCode.EUR.key -> R.drawable.flag_eur
        CurrencyCode.AUD.key -> R.drawable.flag_aud
        CurrencyCode.BGN.key -> R.drawable.flag_bgn
        CurrencyCode.BRL.key -> R.drawable.flag_brl
        CurrencyCode.CAD.key -> R.drawable.flag_cad
        CurrencyCode.CHF.key -> R.drawable.flag_chf
        CurrencyCode.CNY.key -> R.drawable.flag_cny
        CurrencyCode.CZK.key -> R.drawable.flag_czk
        CurrencyCode.DKK.key -> R.drawable.flag_dkk
        CurrencyCode.GBP.key -> R.drawable.flag_gbp
        CurrencyCode.HKD.key -> R.drawable.flag_hkd
        CurrencyCode.HRK.key -> R.drawable.flag_hrk
        CurrencyCode.HUF.key -> R.drawable.flag_huf
        CurrencyCode.IDR.key -> R.drawable.flag_idr
        CurrencyCode.ILS.key -> R.drawable.flag_ils
        CurrencyCode.INR.key -> R.drawable.flag_inr
        CurrencyCode.ISK.key -> R.drawable.flag_isk
        CurrencyCode.JPY.key -> R.drawable.flag_jpy
        CurrencyCode.KRW.key -> R.drawable.flag_krw
        CurrencyCode.MXN.key -> R.drawable.flag_mxn
        CurrencyCode.MYR.key -> R.drawable.flag_myr
        CurrencyCode.NOK.key -> R.drawable.flag_nok
        CurrencyCode.NZD.key -> R.drawable.flag_nzd
        CurrencyCode.PHP.key -> R.drawable.flag_php
        CurrencyCode.PLN.key -> R.drawable.flag_pln
        CurrencyCode.RON.key -> R.drawable.flag_ron
        CurrencyCode.RUB.key -> R.drawable.flag_rub
        CurrencyCode.SEK.key -> R.drawable.flag_sek
        CurrencyCode.SGD.key -> R.drawable.flag_sgd
        CurrencyCode.THB.key -> R.drawable.flag_thb
        CurrencyCode.USD.key -> R.drawable.flag_usd
        CurrencyCode.ZAR.key -> R.drawable.flag_zar
        else -> 0
    }
}