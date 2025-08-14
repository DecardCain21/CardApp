package com.example.cardapp.core.domain.model

public data class CardInf(
    val bin: String,
    val numberLength: Int?,
    val luhn: Boolean,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean,
    val countryNumeric: String?,
    val countryAlpha2: String?,
    val countryName: String?,
    val countryEmoji: String?,
    val countryCurrency: String?,
    val countryLatitude: Int?,
    val countryLongitude: Int?,
    val bankName: String?,
    val bankUrl: String?,
    val bankPhone: String?,
    val bankCity: String?
)