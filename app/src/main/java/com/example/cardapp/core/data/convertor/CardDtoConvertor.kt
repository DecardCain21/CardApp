package com.example.cardapp.core.data.convertor

import com.example.cardapp.core.data.network.dto.CardInfResponse
import com.example.cardapp.core.domain.model.CardInf

public fun CardInfResponse.toCardInf(bin: String): CardInf = CardInf(
    bin = bin,
    numberLength = this.number?.length,
    luhn = this.number?.luhn ?: false,
    scheme = this.scheme,
    type = this.type,
    brand = this.brand,
    prepaid = this.prepaid,
    countryNumeric = this.country?.numeric,
    countryAlpha2 = this.country?.alpha2,
    countryName = this.country?.name,
    countryEmoji = this.country?.emoji,
    countryCurrency = this.country?.currency,
    countryLatitude = this.country?.latitude,
    countryLongitude = this.country?.longitude,
    bankName = this.bank?.name,
    bankUrl = this.bank?.url,
    bankPhone = this.bank?.phone,
    bankCity = this.bank?.city
)