package com.harismexis.rates.model

enum class Currency(
    val id: Long,
    val key: String,
    val description: String
) {
    AUD(0, "AUD", "Australian Dollar"),
    BGN(1, "BGN", "Bulgarian Lev"),
    BRL(2, "BRL", "Brazilian Real"),
    CAD(3, "CAD", "Canadian Dollar"),
    CHF(4, "CHF", "Swiss Franc"),
    CNY(5, "CNY", "Chinese Yuan"),
    CZK(6, "CZK", "Czech Koruna"),
    DKK(7, "DKK", "Danish Krone"),
    EUR(9, "EUR", "Euro"),
    GBP(8, "GBP", "Pound Sterling"),
    HKD(10, "HKD", "Hong Kong Dollar"),
    HRK(11, "HRK", "Croatian Kuna"),
    HUF(12, "HUF", "Hungarian Forint"),
    IDR(13, "IDR", "Indonesian Rupiah"),
    ILS(14, "ILS", "Israeli New Shekel"),
    INR(15, "INR", "Indian Rupee"),
    ISK(16, "ISK", "Icelandic Króna"),
    JPY(17, "JPY", "Japanese Yen"),
    KRW(18, "KRW", "South Korean Won"),
    MXN(19, "MXN", "Mexican Peso"),
    MYR(20, "MYR", "Malaysian Ringgit"),
    NOK(21, "NOK", "Norwegian Krone"),
    NZD(22, "NZD", "New Zealand Dollar"),
    PHP(23, "PHP", "Philippine Peso"),
    PLN(24, "PLN", "Polish Zloty"),
    RON(25, "RON", "Romanian New Leu"),
    RUB(26, "RUB", "Russian Ruble"),
    SEK(27, "SEK", "Swedish Krona"),
    SGD(28, "SGD", "Singapore Dollar"),
    THB(29, "THB", "Thai Baht"),
    USD(30, "USD", "US Dollar"),
    ZAR(31, "ZAR", "South African Rand")
}