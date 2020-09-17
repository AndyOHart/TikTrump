package com.example.tiktrump.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tiktrump.model.ListConverter
import com.example.tiktrump.model.TrumpQuote

@Database(entities = [TrumpQuote::class], version = 3, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class TikTrumpDB : RoomDatabase() {
    abstract fun tikTrumpDao(): TikTrumpDao

    companion object {
        @Volatile
        private var instance: TikTrumpDB? = null

        fun getDatabase(context: Context): TikTrumpDB =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, TikTrumpDB::class.java, "trumpQuoteModel")
                .fallbackToDestructiveMigration()
                .build()
    }
}
