package com.example.cardapp.core.domain.api

import com.example.cardapp.core.domain.model.CardInf

public interface GetCardUseCase {
    public suspend operator fun invoke(bin: String): Result<CardInf>
}