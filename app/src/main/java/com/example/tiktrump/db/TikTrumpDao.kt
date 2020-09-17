package com.example.tiktrump.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tiktrump.model.TrumpQuote

@Dao
interface TikTrumpDao {

    @Query("SELECT * FROM trumpQuoteModel")
    fun getAllTrumpQuotes(): LiveData<List<TrumpQuote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTrumpQuotes(trumpQuotes: List<TrumpQuote>)

    @Query("DELETE FROM trumpQuoteModel")
    fun deleteAllTrumpQuotes()
}