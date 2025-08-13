package com.example.cardapp.features.details.state

import com.example.cardapp.core.domain.model.CardInf

public sealed interface HistoryScreenUiState {
    public data object Empty : HistoryScreenUiState
    public data class Content(val card: List<CardInf>) : HistoryScreenUiState
}