package com.harismexis.rates.extensions

import com.harismexis.rates.model.Currency
import com.harismexis.rates.model.Rates
import java.util.*

fun Rates.currencyMap(): EnumMap<Currency, Float?> {
    val map: EnumMap<Currency, Float?> = EnumMap<Currency, Float?>(Currency::class.java)
    map[Currency.AUD] = this.AUD
    map[Currency.BGN] = this.BGN
    map[Currency.BRL] = this.BRL
    map[Currency.CAD] = this.CAD
    map[Currency.CHF] = this.CHF
    map[Currency.CNY] = this.CNY
    map[Currency.CZK] = this.CZK
    map[Currency.DKK] = this.DKK
    map[Currency.EUR] = this.EUR
    map[Currency.GBP] = this.GBP
    map[Currency.HKD] = this.HKD
    map[Currency.HRK] = this.HRK
    map[Currency.HUF] = this.HUF
    map[Currency.IDR] = this.IDR
    map[Currency.ILS] = this.ILS
    map[Currency.INR] = this.INR
    map[Currency.ISK] = this.ISK
    map[Currency.JPY] = this.JPY
    map[Currency.KRW] = this.KRW
    map[Currency.MXN] = this.MXN
    map[Currency.MYR] = this.MYR
    map[Currency.NOK] = this.NOK
    map[Currency.NZD] = this.NZD
    map[Currency.PHP] = this.PHP
    map[Currency.PLN] = this.PLN
    map[Currency.RON] = this.RON
    map[Currency.RUB] = this.RUB
    map[Currency.SEK] = this.SEK
    map[Currency.SGD] = this.SGD
    map[Currency.THB] = this.THB
    map[Currency.USD] = this.USD
    map[Currency.ZAR] = this.ZAR
    return map
}