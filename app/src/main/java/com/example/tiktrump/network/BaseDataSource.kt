package com.example.tiktrump.network

import android.util.Log
import com.example.tiktrump.utils.NewResult
import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): NewResult<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return NewResult.success(body)
                }
            }

            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): NewResult<T> {
        Log.d("BaseDataSource", message)
        return NewResult.error("Network called failed due to:  $message")
    }
}