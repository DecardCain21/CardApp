package com.example.cardapp.core.domain.api

import com.example.cardapp.core.domain.model.CardInf

public interface CardInfRepository {

    public suspend fun getCardInf(bin: String): Result<CardInf>
}