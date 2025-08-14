package com.example.cardapp.core.domain.usecase

import com.example.cardapp.core.domain.api.CardInfRepository
import com.example.cardapp.core.domain.api.GetCardUseCase
import com.example.cardapp.core.domain.model.CardInf
import javax.inject.Inject

public class GetCardUseCaseImpl @Inject constructor(
    private val cardInfRepository: CardInfRepository
) : GetCardUseCase {

    public override suspend operator fun invoke(bin: String): Result<CardInf> {
        return cardInfRepository.getCardInf(bin)
    }
}