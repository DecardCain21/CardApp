package com.example.cardapp.features.history.state

import com.example.cardapp.core.domain.model.CardInf

public sealed interface HistoryScreenUiEvent {

    public data class OnDeleteLongClick(val cardInf: CardInf) : HistoryScreenUiEvent
}