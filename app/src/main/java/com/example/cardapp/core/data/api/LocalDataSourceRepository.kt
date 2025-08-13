package com.example.cardapp.core.data.api

import com.example.cardapp.core.domain.model.CardInf
import kotlinx.coroutines.flow.Flow

public interface LocalDataSourceRepository {

    public fun getAllCardInf(): Flow<List<CardInf>>

    public suspend fun addCardInf(cardInf: CardInf)

    public suspend fun delete(cardInf: CardInf)
}