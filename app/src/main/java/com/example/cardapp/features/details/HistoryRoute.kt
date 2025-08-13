package com.example.cardapp.features.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cardapp.features.details.state.HistoryScreenUiEvent

@Composable
public fun HistoryRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HistoryScreen(
        modifier = modifier,
        uiState = uiState,
        onDeleteCardInfLongClick = {
            viewModel.handleEvent(
                HistoryScreenUiEvent.OnDeleteLongClick(it)
            )
        },
        onBack = onBack,
        /*navController = findNavController()*/
    )

}