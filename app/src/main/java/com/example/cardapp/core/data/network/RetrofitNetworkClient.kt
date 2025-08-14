package com.example.cardapp.core.data.network

import com.example.cardapp.core.data.network.api.NetworkClient
import com.example.cardapp.util.NetworkError
import com.example.cardapp.util.getConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

public abstract class RetrofitNetworkClient : NetworkClient {

    public override suspend fun <T> doRequest(request: suspend () -> T): Result<T> {
        if (!getConnected()) {
            return Result.failure(NetworkError.NoInternet())
        }
        return withContext(Dispatchers.IO) {
            try {
                Result.success(request())
            } catch (e: HttpException) {
                when (e.code()) {
                    404 -> Result.failure(NetworkError.NoData())
                    else -> Result.failure(NetworkError.ServerError(e.message()))
                }
            }
        }
    }
}