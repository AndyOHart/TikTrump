package com.example.tiktrump.utils

data class NewResult<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
    }

    companion object {
        fun <T> success(data: T): NewResult<T> {
            return NewResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): NewResult<T> {
            return NewResult(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): NewResult<T> {
            return NewResult(Status.LOADING, data, null)
        }
    }

}