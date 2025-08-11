package com.example.cardapp.core.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.cardapp.core.data.local.LocalDataSourseImpl
import com.example.cardapp.core.data.local.dao.CardInfHistoryDao
import com.example.cardapp.core.data.local.db.AppDatabase
import com.example.cardapp.core.data.repository.LocalDataSourceRepositoryImpl
import com.example.cardapp.core.domain.api.LocalDataSource
import com.example.cardapp.core.domain.api.LocalDataSourceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public class LocalSourceModuleProvider {

    @Provides
    public fun provideNoteDao(database: AppDatabase): CardInfHistoryDao = database.cardInfHistory()

    @Provides
    @Singleton
    public fun providesLocalDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "card_inf_history-database"
    ).fallbackToDestructiveMigration().build()
}

@Module
@InstallIn(SingletonComponent::class)
public abstract class LocalSourceModuleBinder {

    @Binds
    public abstract fun bindRoomLocalDataSource(
        roomLocalDataSource: LocalDataSourseImpl
    ): LocalDataSource

    @Binds
    public abstract fun bindLocalDaraSourceRepository(
        localDaraSourceRepository: LocalDataSourceRepositoryImpl
    ): LocalDataSourceRepository
}