package com.example.cardapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cardapp.core.data.local.entity.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
public interface CardInfHistoryDao {

    @Query("SELECT * FROM cardentity")
    public fun getAllCardInf() : Flow<List<CardEntity>>

    @Insert(entity = CardEntity::class, onConflict = OnConflictStrategy.REPLACE)
    public fun addCardInf(cardInf: CardEntity)

    @Delete
    public fun delete(cardInf: CardEntity)
}