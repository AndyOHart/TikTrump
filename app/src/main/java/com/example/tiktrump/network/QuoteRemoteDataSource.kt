package com.example.tiktrump.network

import javax.inject.Inject

class QuoteRemoteDataSource @Inject constructor(
    private val service: TikTrumpApiService
) : BaseDataSource() {

    suspend fun getAllTrumpQuotes() = getResult { service.getTrumpQuotes() }
    suspend fun getRandomTrumpQuote() = getResult { service.getRandomTrumpQuote() }
}