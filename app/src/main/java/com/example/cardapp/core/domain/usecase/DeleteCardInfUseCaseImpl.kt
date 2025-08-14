package com.example.cardapp.core.domain.usecase

import com.example.cardapp.core.domain.api.DeleteCardInfUseCase
import com.example.cardapp.core.data.api.LocalDataSourceRepository
import com.example.cardapp.core.domain.model.CardInf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

public class DeleteCardInfUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : DeleteCardInfUseCase {

    override suspend fun invoke(cardInf: CardInf): Unit =
        withContext(Dispatchers.IO) {
            localDataSourceRepository.delete(cardInf)
        }
}