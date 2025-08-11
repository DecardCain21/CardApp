package com.example.cardapp.core.data.network.api

public interface NetworkClient {
    public suspend fun <T> doRequest(request: suspend () -> T): Result<T>
}