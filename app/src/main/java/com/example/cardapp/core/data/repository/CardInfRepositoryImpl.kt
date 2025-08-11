package com.example.cardapp.core.data.repository

import com.example.cardapp.core.data.convertor.toCardInf
import com.example.cardapp.core.data.network.client.CardInfNetworkClient
import com.example.cardapp.core.domain.api.CardInfRepository
import com.example.cardapp.core.domain.api.LocalDataSource
import com.example.cardapp.core.domain.model.CardInf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

public class CardInfRepositoryImpl @Inject constructor(
    private val cardInfNetworkClient: CardInfNetworkClient,
    private val localDataSource: LocalDataSource
) : CardInfRepository {

    public override suspend fun getCardInf(bin: String): Result<CardInf> =
        withContext(Dispatchers.IO) {
            cardInfNetworkClient.execute(bin)
                .fold(
                    onSuccess = { cardInf ->
                        val res = cardInf.toCardInf(bin)
                        localDataSource.addCardInf(res)
                        Result.success(res)
                    },
                    onFailure = { error ->
                        Result.failure(error)
                    }
                )
        }
}