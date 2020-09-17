package com.example.tiktrump.di

import android.content.Context
import com.example.tiktrump.db.TikTrumpDB
import com.example.tiktrump.db.TikTrumpDao
import com.example.tiktrump.network.QuoteRemoteDataSource
import com.example.tiktrump.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTikTrumpDB(@ApplicationContext context: Context): TikTrumpDB =
        TikTrumpDB.getDatabase(context)

    @Singleton
    @Provides
    fun provideTikTrumpDao(tikTrumpDB: TikTrumpDB): TikTrumpDao = tikTrumpDB.tikTrumpDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: QuoteRemoteDataSource, localDataSource: TikTrumpDao) =
        Repository(remoteDataSource, localDataSource)
}