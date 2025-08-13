package com.example.cardapp.features.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cardapp.R
import com.example.cardapp.core.composable.BankInfoSection
import com.example.cardapp.core.composable.CardInfoSection
import com.example.cardapp.core.composable.CountryInfoSection
import com.example.cardapp.core.composable.InfoRow
import com.example.cardapp.core.composable.SectionTitle
import com.example.cardapp.core.domain.model.CardInf
import com.example.cardapp.features.details.state.HistoryScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun HistoryScreen(
    modifier: Modifier = Modifier,
    uiState: HistoryScreenUiState,
    onDeleteCardInfLongClick: (CardInf) -> Unit,
    /*navController: NavController,*/
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("История запросов") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (uiState) {
                is HistoryScreenUiState.Empty -> {
                    HistoryScreenEmpty(modifier = modifier)
                }

                is HistoryScreenUiState.Content -> {
                    HistoryScreenContent(
                        modifier = modifier,
                        cardList = uiState.card,
                        onDeleteCardInfLongClick = onDeleteCardInfLongClick
                    )
                }
            }
        }
    }
}

@Composable
public fun HistoryScreenEmpty(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.history_is_empty),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
public fun HistoryScreenContent(
    modifier: Modifier = Modifier,
    cardList: List<CardInf>,
    onDeleteCardInfLongClick: (CardInf) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        itemsIndexed(cardList) { index,card ->
            ExpandableCard(
                cardName = index+1,
                card = card,
                onDeleteCardInfLongClick = onDeleteCardInfLongClick
            )
        }
    }
}

@Composable
public fun ExpandableCard(
    modifier: Modifier = Modifier,
    cardName:Int,
    card: CardInf,
    onDeleteCardInfLongClick: (CardInf) -> Unit,
) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { expanded = !expanded },
                onLongClick = { onDeleteCardInfLongClick(card) },
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            SectionTitle(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                titleRes = R.string.card_info_title,
                titleName = "Card №$cardName"
            )
            InfoRow(labelRes = R.string.bin, value = card.bin)
            CardInfoSection(card = card)

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    SectionTitle(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        titleRes = R.string.country_info_title
                    )
                    CountryInfoSection(card = card)

                    Spacer(modifier = Modifier.height(8.dp))

                    SectionTitle(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        titleRes = R.string.bank_info_title
                    )
                    BankInfoSection(card = card)
                }
            }

            Icon(
                imageVector = if (expanded) {
                    Icons.Default.KeyboardArrowDown
                } else
                    Icons.Default.KeyboardArrowUp,
                contentDescription = if (expanded) {
                    stringResource(R.string.collapse)
                } else stringResource(
                    R.string.expand
                ),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

