package com.example.cardapp.core.data.network.dto

public class CardInfResponse(
    public val bank: BankDto?,
    public val brand: String?,
    public val country: CountryDto?,
    public val number: NumberDto?,
    public val prepaid: Boolean,
    public val scheme: String?,
    public val type: String?
)

public class CountryDto(
    public val alpha2: String?,
    public val currency: String?,
    public val emoji: String?,
    public val latitude: Int?,
    public val longitude: Int?,
    public val name: String?,
    public val numeric: String?
)

public class NumberDto(
    public val length: Int?,
    public val luhn: Boolean
)

public class BankDto(
    public val city: String?,
    public val name: String?,
    public val phone: String?,
    public val url: String?
)