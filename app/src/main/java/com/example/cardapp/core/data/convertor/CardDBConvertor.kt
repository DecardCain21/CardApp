package com.example.cardapp.core.data.convertor

import com.example.cardapp.core.data.local.entity.CardEntity
import com.example.cardapp.core.domain.model.CardInf

public fun CardEntity.toCardInf(): CardInf = CardInf(
    bin = bin.toString(),
    numberLength = numberLength,
    luhn = luhn,
    scheme = scheme,
    type = type,
    brand = brand,
    prepaid = prepaid,
    countryNumeric = countryNumeric,
    countryAlpha2 = countryAlpha2,
    countryName = countryName,
    countryEmoji = countryEmoji,
    countryCurrency = countryCurrency,
    countryLatitude = countryLatitude,
    countryLongitude = countryLongitude,
    bankName = bankName,
    bankUrl = bankUrl,
    bankPhone = bankPhone,
    bankCity = bankCity
)

public fun CardInf.toCardInfEntity(): CardEntity = CardEntity(
    bin = bin.toInt(),
    numberLength = numberLength,
    luhn = luhn,
    scheme = scheme,
    type = type,
    brand = brand,
    prepaid = prepaid,
    countryNumeric = countryNumeric,
    countryAlpha2 = countryAlpha2,
    countryName = countryName,
    countryEmoji = countryEmoji,
    countryCurrency = countryCurrency,
    countryLatitude = countryLatitude,
    countryLongitude = countryLongitude,
    bankName = bankName,
    bankUrl = bankUrl,
    bankPhone = bankPhone,
    bankCity = bankCity
)