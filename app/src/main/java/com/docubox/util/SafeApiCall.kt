package com.docubox.util

import retrofit2.Response
import timber.log.Timber
import java.io.IOException

suspend fun <T> safeApiCall(
    successMessage: String = "",
    errorMessage: String? = null,
    call: suspend () -> Response<T>
): Resource<T> = try {
    val response = call()
    if (response.isSuccessful) {
        response.body()?.let { Resource.Success(it, successMessage) }
            ?: Resource.Error(message = errorMessage ?: response.message())
    } else Resource.Error(message = errorMessage ?: response.message())
} catch (e: IOException) {
    Timber.d(e.toString())
    Resource.Error(ErrorType.NoInternet)
} catch (e: Exception) {
    Timber.d(e.toString())
    Resource.Error(ErrorType.Unknown, message = errorMessage ?: e.message.toString())
}