package com.example.rates.extensions

import androidx.annotation.DrawableRes
import com.example.rates.R
import com.example.rates.model.Currency

@DrawableRes
fun String?.getFlagForCurrency(): Int {
    return when (this) {
        Currency.AUD.key -> R.drawable.flag_aud
        Currency.BGN.key -> R.drawable.flag_bgn
        Currency.BRL.key -> R.drawable.flag_brl
        Currency.CAD.key -> R.drawable.flag_cad
        Currency.CHF.key -> R.drawable.flag_chf
        Currency.CNY.key -> R.drawable.flag_cny
        Currency.CZK.key -> R.drawable.flag_czk
        Currency.DKK.key -> R.drawable.flag_dkk
        Currency.EUR.key -> R.drawable.flag_eur
        Currency.GBP.key -> R.drawable.flag_gbp
        Currency.HKD.key -> R.drawable.flag_hkd
        Currency.HRK.key -> R.drawable.flag_hrk
        Currency.HUF.key -> R.drawable.flag_huf
        Currency.IDR.key -> R.drawable.flag_idr
        Currency.ILS.key -> R.drawable.flag_ils
        Currency.INR.key -> R.drawable.flag_inr
        Currency.ISK.key -> R.drawable.flag_isk
        Currency.JPY.key -> R.drawable.flag_jpy
        Currency.KRW.key -> R.drawable.flag_krw
        Currency.MXN.key -> R.drawable.flag_mxn
        Currency.MYR.key -> R.drawable.flag_myr
        Currency.NOK.key -> R.drawable.flag_nok
        Currency.NZD.key -> R.drawable.flag_nzd
        Currency.PHP.key -> R.drawable.flag_php
        Currency.PLN.key -> R.drawable.flag_pln
        Currency.RON.key -> R.drawable.flag_ron
        Currency.RUB.key -> R.drawable.flag_rub
        Currency.SEK.key -> R.drawable.flag_sek
        Currency.SGD.key -> R.drawable.flag_sgd
        Currency.THB.key -> R.drawable.flag_thb
        Currency.USD.key -> R.drawable.flag_usd
        Currency.ZAR.key -> R.drawable.flag_zar
        else -> 0
    }
}