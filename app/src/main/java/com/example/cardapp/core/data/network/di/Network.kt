package com.example.cardapp.core.data.network.di

import com.example.cardapp.core.data.network.AuthInterceptor
import com.example.cardapp.core.data.network.client.CardInfNetworkClient
import com.example.cardapp.core.data.network.api.BinListApiService
import com.example.cardapp.core.data.repository.CardInfRepositoryImpl
import com.example.cardapp.core.domain.api.CardInfRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

public const val BIN_LIST_BASE_URL: String = "https://lookup.binlist.net/"

@Module
@InstallIn(SingletonComponent::class)
public object NetworkModuleProvider {

    @Provides
    @Singleton
    public fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor)
            .build()
    }

    @Provides
    @Singleton
    public fun provideHeadHunterApiService(
        okHttpClient: OkHttpClient
    ): BinListApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BIN_LIST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinListApiService::class.java)
    }

    @Provides
    @Singleton
    public fun provideCardInfNetworkClient(
        binListApiService: BinListApiService
    ): CardInfNetworkClient {
        return CardInfNetworkClient(binListApiService)
    }
}

@Module
@InstallIn(SingletonComponent::class)
public abstract class NetworkModuleBinder() {

    @Binds
    public abstract fun bindCardInfRepository(
        cardInfRepositoryImpl: CardInfRepositoryImpl
    ): CardInfRepository
}