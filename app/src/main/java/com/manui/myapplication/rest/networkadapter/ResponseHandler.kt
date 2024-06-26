package com.manui.myapplication.rest.networkadapter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.lang.reflect.Type

internal object ResponseHandler {

    fun <S : Any, E : Any> handle(
        response: Response<S>,
        successBodyType: Type,
        errorConverter: Converter<ResponseBody, E>
    ): NetworkResponse<S, E> {
        val body = response.body()
        val headers = response.headers()
        val code = response.code()
        val errorBody = response.errorBody()

        return if (response.isSuccessful) {
            if (body != null) {
                NetworkResponse.Success(body, headers, code)
            } else {
                // Special case: If the response is successful and the body is null, return a successful response
                // if the service method declares the success body type as Unit. Otherwise, return a server error
                if (successBodyType == Unit::class.java) {
                    @Suppress("UNCHECKED_CAST")
                    NetworkResponse.Success(Unit, headers, code) as NetworkResponse<S, E>
                } else {
                    NetworkResponse.ServerError(null, code, headers)
                }
            }
        } else {
            val networkResponse: NetworkResponse<S, E> = try {
                val convertedBody = errorConverter.convert(errorBody)
                NetworkResponse.ServerError(convertedBody, code, headers)
            } catch (ex: Exception) {
                NetworkResponse.UnknownError(ex)
            }
            networkResponse
        }
    }

}