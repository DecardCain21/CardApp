package com.example.cardapp.core.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cardapp.R
import com.example.cardapp.core.domain.model.CardInf

@Composable
public fun SectionTitle(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    titleName: String = ""
) {
    val textTittle = if (titleName.isNullOrEmpty())stringResource(titleRes) else titleName
    Text(
        modifier = modifier,
        text = textTittle,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
public fun CardInfoSection(card: CardInf) {
    InfoRow(labelRes = R.string.number_length, value = card.numberLength?.toString())
    InfoRow(
        labelRes = R.string.luhn_check,
        value = if (card.luhn)
            stringResource(id = R.string.yes)
        else
            stringResource(id = R.string.no)
    )
    /*InfoRow(labelRes = R.string.scheme, value = card.scheme)*/
    /*InfoRow(labelRes = R.string.type, value = card.type)*/
    InfoRow(labelRes = R.string.brand, value = card.brand)
    InfoRow(
        labelRes = R.string.prepaid,
        value = if (card.prepaid)
            stringResource(id = R.string.yes)
        else
            stringResource(id = R.string.no)
    )
}

@Composable
public fun CountryInfoSection(card: CardInf) {
    InfoRow(labelRes = R.string.country_numeric, value = card.countryNumeric)
    InfoRow(labelRes = R.string.country_code, value = card.countryAlpha2)
    InfoRow(labelRes = R.string.country_name, value = card.countryName)
    InfoRow(labelRes = R.string.country_emoji, value = card.countryEmoji)
    InfoRow(labelRes = R.string.currency, value = card.countryCurrency)
    InfoRow(labelRes = R.string.latitude, value = card.countryLatitude?.toString())
    InfoRow(labelRes = R.string.longitude, value = card.countryLongitude?.toString())
}

@Composable
public fun BankInfoSection(card: CardInf) {
    InfoRow(labelRes = R.string.bank_name, value = card.bankName)
    InfoRow(labelRes = R.string.bank_url, value = card.bankUrl)
    InfoRow(labelRes = R.string.bank_phone, value = card.bankPhone)
    InfoRow(labelRes = R.string.bank_city, value = card.bankCity)
}

@Composable
public fun TextItem(label: String, value: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = value ?: stringResource(id = R.string.no),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
public fun InfoRow(@StringRes labelRes: Int, value: String?) {
    TextItem(
        label = stringResource(labelRes),
        value = value ?: stringResource(R.string.no)
    )
}