package com.example.tiktrump.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListConverter {

    @TypeConverter
    fun listToJson(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList() as ArrayList<String>
}