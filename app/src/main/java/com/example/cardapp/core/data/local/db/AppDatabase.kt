package com.example.cardapp.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cardapp.core.data.local.dao.CardInfHistoryDao
import com.example.cardapp.core.data.local.entity.CardEntity

@Database(entities = [CardEntity::class], version = 1)
public abstract class AppDatabase : RoomDatabase() {
    public abstract fun cardInfHistory(): CardInfHistoryDao
}