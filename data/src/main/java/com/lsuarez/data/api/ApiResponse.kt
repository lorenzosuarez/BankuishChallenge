package com.lsuarez.data.api

import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Created by Lorenzo on 12/5/2022.
 */

sealed class ApiResponse<out Response> {
    data class Success<Response> (val value : Response): ApiResponse<Response>()
    data class Failure(val statusCode: Int): ApiResponse<Nothing>()
}

fun <Result : Any> Response<Result>.parseResponse(): ApiResponse<Result> {
    if (isSuccessful) {
        val body = body()
        if (body != null) {
            return ApiResponse.Success(body)
        }
    } else {
        return ApiResponse.Failure(code())
    }
    return ApiResponse.Failure(HttpURLConnection.HTTP_INTERNAL_ERROR)
}