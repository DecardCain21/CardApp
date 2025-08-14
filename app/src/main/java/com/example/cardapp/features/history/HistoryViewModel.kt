package com.example.cardapp.features.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardapp.core.domain.api.DeleteCardInfUseCase
import com.example.cardapp.core.domain.api.GetAllCacheCardUseCase
import com.example.cardapp.core.domain.model.CardInf
import com.example.cardapp.features.history.state.HistoryScreenUiEvent
import com.example.cardapp.features.history.state.HistoryScreenUiState
import com.example.cardapp.util.debounce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class HistoryViewModel @Inject constructor(
    getAllCacheCardUseCase: GetAllCacheCardUseCase,
    private val deleteCardInfUseCase: DeleteCardInfUseCase
) : ViewModel() {
    public fun handleEvent(event: HistoryScreenUiEvent) {
        when (event) {
            is HistoryScreenUiEvent.OnDeleteLongClick -> searchDebounceAction(event.cardInf)
        }
    }

    private val cards = getAllCacheCardUseCase()

    public val uiState: StateFlow<HistoryScreenUiState> = cards.map { cardInfList ->
        if (cardInfList.isNotEmpty())
            HistoryScreenUiState.Content(cardInfList.reversed())
        else HistoryScreenUiState.Empty
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HistoryScreenUiState.Empty
    )

    private val searchDebounceAction: (CardInf) -> Unit = debounce(
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { cardInf -> deleteCardInf(cardInf) }

    private fun deleteCardInf(cardInf: CardInf) {
        viewModelScope.launch {
            deleteCardInfUseCase(cardInf)
        }
    }
}