package com.example.cardapp.core.data.repository

import com.example.cardapp.core.domain.api.LocalDataSource
import com.example.cardapp.core.domain.api.LocalDataSourceRepository
import com.example.cardapp.core.domain.model.CardInf
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class LocalDataSourceRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : LocalDataSourceRepository {

    public override fun getAllCardInf(): Flow<List<CardInf>> = localDataSource.getAllCardInf()

    public override suspend fun addCardInf(cardInf: CardInf): Unit = localDataSource.addCardInf(cardInf)

    public override suspend fun delete(cardInf: CardInf): Unit = localDataSource.delete(cardInf)
}