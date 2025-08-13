package com.example.cardapp.core.domain.api

import com.example.cardapp.core.domain.model.CardInf
import kotlinx.coroutines.flow.Flow

public interface GetAllCacheCardUseCase {

    public operator fun invoke(): Flow<List<CardInf>>
}