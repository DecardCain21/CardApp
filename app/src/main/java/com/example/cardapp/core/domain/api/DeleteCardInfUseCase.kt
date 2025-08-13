package com.example.cardapp.core.domain.api

import com.example.cardapp.core.domain.model.CardInf

public interface DeleteCardInfUseCase {

    public suspend operator fun invoke(cardInf: CardInf)
}