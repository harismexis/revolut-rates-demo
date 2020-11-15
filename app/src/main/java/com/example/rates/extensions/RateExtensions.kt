package com.example.rates.extensions

import com.example.rates.model.CurrencyCode
import com.example.rates.model.Rates
import java.util.*

fun Rates.currencyMap(): EnumMap<CurrencyCode, Float?> {
    val map: EnumMap<CurrencyCode, Float?> = EnumMap<CurrencyCode, Float?>(CurrencyCode::class.java)
    map[CurrencyCode.AUD] = this.AUD
    map[CurrencyCode.BGN] = this.BGN
    map[CurrencyCode.BRL] = this.BRL
    map[CurrencyCode.CAD] = this.CAD
    map[CurrencyCode.CHF] = this.CHF
    map[CurrencyCode.CNY] = this.CNY
    map[CurrencyCode.CZK] = this.CZK
    map[CurrencyCode.DKK] = this.DKK
    map[CurrencyCode.EUR] = this.EUR
    map[CurrencyCode.GBP] = this.GBP
    map[CurrencyCode.HKD] = this.HKD
    map[CurrencyCode.HRK] = this.HRK
    map[CurrencyCode.HUF] = this.HUF
    map[CurrencyCode.IDR] = this.IDR
    map[CurrencyCode.ILS] = this.ILS
    map[CurrencyCode.INR] = this.INR
    map[CurrencyCode.ISK] = this.ISK
    map[CurrencyCode.JPY] = this.JPY
    map[CurrencyCode.KRW] = this.KRW
    map[CurrencyCode.MXN] = this.MXN
    map[CurrencyCode.MYR] = this.MYR
    map[CurrencyCode.NOK] = this.NOK
    map[CurrencyCode.NZD] = this.NZD
    map[CurrencyCode.PHP] = this.PHP
    map[CurrencyCode.PLN] = this.PLN
    map[CurrencyCode.RON] = this.RON
    map[CurrencyCode.RUB] = this.RUB
    map[CurrencyCode.SEK] = this.SEK
    map[CurrencyCode.SGD] = this.SGD
    map[CurrencyCode.THB] = this.THB
    map[CurrencyCode.USD] = this.USD
    map[CurrencyCode.ZAR] = this.ZAR
    return map
}