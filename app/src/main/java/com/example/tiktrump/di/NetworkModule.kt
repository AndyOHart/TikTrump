package com.example.tiktrump.di

import com.example.tiktrump.network.QuoteRemoteDataSource
import com.example.tiktrump.network.TikTrumpApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetroFit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.whatdoestrumpthink.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideTikTrumpService(retrofit: Retrofit): TikTrumpApiService =
        retrofit.create(TikTrumpApiService::class.java)

    @Singleton
    @Provides
    fun provideQuotesRemoteDataSource(service: TikTrumpApiService) = QuoteRemoteDataSource(service)
}