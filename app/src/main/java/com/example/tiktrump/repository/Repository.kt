package com.example.tiktrump.repository

import androidx.annotation.WorkerThread
import com.example.tiktrump.db.TikTrumpDao
import com.example.tiktrump.model.TrumpQuote
import com.example.tiktrump.network.QuoteRemoteDataSource
import com.example.tiktrump.utils.TrumpFaceImageProvider
import com.example.tiktrump.utils.performGetOperation
import kotlinx.coroutines.*
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteSource: QuoteRemoteDataSource,
    private val dao: TikTrumpDao
) {

    val trumpQuotesOldWay = performGetOperation(
        databaseQuery = { dao.getAllTrumpQuotes() },
        networkCall = { remoteSource.getAllTrumpQuotes() },
        saveCallResult = {
            val trumpQuotesList = ArrayList<TrumpQuote>()

            for (messageString in it.messages.non_personalized) {
                trumpQuotesList.add(
                    TrumpQuote(
                        messageString,
                        TrumpFaceImageProvider().getRandomTrumpFace(),
                        false
                    )
                )
            }

            dao.insertAllTrumpQuotes(trumpQuotesList)
        }
    )

    @WorkerThread
    suspend fun deleteAllQuotes() = withContext(Dispatchers.IO) { dao.deleteAllTrumpQuotes() }

}