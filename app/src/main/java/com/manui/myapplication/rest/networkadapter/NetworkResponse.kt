package com.manui.myapplication.rest.networkadapter

import okhttp3.Headers
import java.io.IOException

sealed class NetworkResponse<out T : Any, out U : Any> {

    data class Success<T : Any>(
        val body: T,
        val headers: Headers? = null,
        val code: Int
    ) : NetworkResponse<T, Nothing>()

    data class ServerError<U : Any>(
        val body: U?,
        val code: Int,
        val headers: Headers? = null
    ) : NetworkResponse<Nothing, U>()

    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    data class UnknownError(val error: Throwable) : NetworkResponse<Nothing, Nothing>()

    object Loading : NetworkResponse<Nothing, Nothing>()
    object NotExecuted : NetworkResponse<Nothing, Nothing>()
}