package com.example.tiktrump.model

import androidx.room.ColumnInfo

data class Messages(
    @ColumnInfo(name = "personalized_id") val personalized: ArrayList<String>,
    @ColumnInfo(name = "non_personalized_id")val non_personalized: ArrayList<String>
)