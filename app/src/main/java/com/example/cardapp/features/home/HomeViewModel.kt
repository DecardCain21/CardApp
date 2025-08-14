package com.example.cardapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardapp.R
import com.example.cardapp.core.domain.api.GetCardUseCase
import com.example.cardapp.core.domain.model.CardInf
import com.example.cardapp.features.home.state.HomeScreenUiState
import com.example.cardapp.util.NetworkError
import com.example.cardapp.util.debounce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class HomeViewModel @Inject constructor(
    private val getCardUseCase: GetCardUseCase
) : ViewModel() {

    private var lastExpression = ""

    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Start)
    public val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    private val _inputError = MutableStateFlow(false)
    public val inputError: StateFlow<Boolean> = _inputError.asStateFlow()

    private val searchDebounceAction: (String) -> Unit = debounce(
        delayMillis = 2_000L,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { changedText ->
        when {
            changedText.length < 6 -> {
                _inputError.value = true
                _uiState.value = HomeScreenUiState.Error(R.string.enter_the_first_6_8_digits)
            }

            changedText != lastExpression -> {
                _inputError.value = false
                lastExpression = changedText
                getCardInformation(changedText)
            }
        }
    }

    public fun searchDebounce(expression: String) {
        if (expression.isBlank()) {
            clearSearch()
            _inputError.value = false
        } else {
            searchDebounceAction(expression)
        }
    }

    private fun clearSearch() {
        lastExpression = ""
        _uiState.value = HomeScreenUiState.Start
    }

    public fun getCardInformation(bin: String): Job = viewModelScope.launch(Dispatchers.Main) {
        _uiState.value = HomeScreenUiState.Loading
        val result: Result<CardInf> = getCardUseCase(bin.replace("\\s".toRegex(), ""))
        val newState = when (result.exceptionOrNull()) {
            is NetworkError.ServerError -> HomeScreenUiState.Error(R.string.server_error)
            is NetworkError.NoData -> HomeScreenUiState.Start
            is NetworkError.NoInternet -> HomeScreenUiState.NoInternet
            else -> result.getOrNull()?.let {
                HomeScreenUiState.Content(it)
            } ?: HomeScreenUiState.Start
        }
        _uiState.value = newState
    }
}