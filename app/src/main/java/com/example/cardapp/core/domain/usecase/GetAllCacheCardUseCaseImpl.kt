package com.example.cardapp.core.domain.usecase

import com.example.cardapp.core.domain.api.GetAllCacheCardUseCase
import com.example.cardapp.core.domain.api.LocalDataSourceRepository
import com.example.cardapp.core.domain.model.CardInf
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class GetAllCacheCardUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
): GetAllCacheCardUseCase {

    override fun invoke(): Flow<List<CardInf>> = localDataSourceRepository.getAllCardInf()
}