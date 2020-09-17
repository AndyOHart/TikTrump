package com.example.tiktrump.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> NewResult<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<NewResult<T>> =
    liveData(Dispatchers.IO) {
        emit(NewResult.loading())
        val source = databaseQuery.invoke().map { NewResult.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == NewResult.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == NewResult.Status.ERROR) {
            emit(NewResult.error(responseStatus.message!!))
            emitSource(source)
        }
    }