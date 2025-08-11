package com.example.cardapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public data class CardEntity (
    @PrimaryKey(autoGenerate = false)
    val bin: Int,
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