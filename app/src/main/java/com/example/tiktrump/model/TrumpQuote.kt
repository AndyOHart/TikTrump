package com.example.tiktrump.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trumpQuoteModel")
data class TrumpQuote(val message: String, val trumpFaceResId: Int, val isFavourite: Boolean) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}