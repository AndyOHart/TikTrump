package com.example.tiktrump.network

import com.example.tiktrump.model.TrumpQuote
import com.example.tiktrump.model.TrumpQuotes
import retrofit2.Response
import retrofit2.http.GET

interface TikTrumpApiService {

    @GET("v1/quotes")
    suspend fun getTrumpQuotes(): Response<TrumpQuotes>

    @GET("v1/quotes/random")
    suspend fun getRandomTrumpQuote(): Response<TrumpQuote>
}