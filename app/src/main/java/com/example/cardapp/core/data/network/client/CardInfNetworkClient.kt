package com.example.cardapp.core.data.network.client

import com.example.cardapp.core.data.network.RetrofitNetworkClient
import com.example.cardapp.core.data.network.api.BinListApiService
import com.example.cardapp.core.data.network.dto.CardInfResponse


public class CardInfNetworkClient(
    private val binListApiService: BinListApiService,
) : RetrofitNetworkClient() {

    public suspend fun execute(bin: String): Result<CardInfResponse> =
        super.doRequest { binListApiService.getCardInfo(bin) }
}