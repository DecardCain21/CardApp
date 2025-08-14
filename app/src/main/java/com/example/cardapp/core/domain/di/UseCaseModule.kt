package com.example.cardapp.core.domain.di

import com.example.cardapp.core.domain.api.DeleteCardInfUseCase
import com.example.cardapp.core.domain.api.GetAllCacheCardUseCase
import com.example.cardapp.core.domain.api.GetCardUseCase
import com.example.cardapp.core.domain.usecase.DeleteCardInfUseCaseImpl
import com.example.cardapp.core.domain.usecase.GetAllCacheCardUseCaseImpl
import com.example.cardapp.core.domain.usecase.GetCardUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
public abstract class UseCaseModule {

    @Binds
    public abstract fun bindGetCardUseCase(
        getCardUseCase: GetCardUseCaseImpl
    ): GetCardUseCase

    @Binds
    public abstract fun bindGetAllCacheCardUseCase(
        getAllCacheCardUseCaseImpl: GetAllCacheCardUseCaseImpl
    ): GetAllCacheCardUseCase

    @Binds
    public abstract fun bindDeleteCardInfUseCase(
        deleteCardInfUseCaseImpl: DeleteCardInfUseCaseImpl
    ): DeleteCardInfUseCase
}