package com.example.cardapp.core.data.local

import com.example.cardapp.core.data.convertor.toCardInf
import com.example.cardapp.core.data.convertor.toCardInfEntity
import com.example.cardapp.core.data.local.dao.CardInfHistoryDao
import com.example.cardapp.core.data.api.LocalDataSource
import com.example.cardapp.core.domain.model.CardInf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

public class LocalDataSourseImpl @Inject constructor(
    private val cardInfHistoryDao: CardInfHistoryDao
) : LocalDataSource {

    override fun getAllCardInf(): Flow<List<CardInf>> {
        return cardInfHistoryDao.getAllCardInf().map { cardEntityList ->
            cardEntityList.map { cardEntity -> cardEntity.toCardInf() }
        }
    }


    override suspend fun addCardInf(cardInf: CardInf) {
        cardInfHistoryDao.addCardInf(cardInf.toCardInfEntity())
    }


    override suspend fun delete(cardInf: CardInf) {
        cardInfHistoryDao.delete(cardInf.toCardInfEntity())
    }
}