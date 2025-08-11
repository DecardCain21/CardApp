package com.example.cardapp.util

public sealed class NetworkError(override val message: String) : Throwable(message = message) {

    public class ServerError(message: String) : NetworkError("Error communicating with the server message: $message")

    public class NoData : NetworkError("Empty response body")

    public class NoInternet : NetworkError("No internet connection")
}