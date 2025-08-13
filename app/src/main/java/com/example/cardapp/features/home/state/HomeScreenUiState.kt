package com.example.cardapp.features.home.state

import androidx.annotation.StringRes
import com.example.cardapp.core.domain.model.CardInf

public sealed interface HomeScreenUiState {
    public data object Start : HomeScreenUiState
    public data class Error(@StringRes val messageId: Int) : HomeScreenUiState
    public data object Loading : HomeScreenUiState
    public data object NoInternet : HomeScreenUiState
    public data class Content(val card: CardInf) : HomeScreenUiState
}