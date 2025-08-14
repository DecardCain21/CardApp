package com.example.cardapp.core.data.network.api

import com.example.cardapp.core.data.network.dto.CardInfResponse
import retrofit2.http.GET
import retrofit2.http.Path

public interface BinListApiService {

    @GET("{bin}")
    public suspend fun getCardInfo(
        @Path("bin") bin: String
    ): CardInfResponse
}