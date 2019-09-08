package com.saeed.android.scoreline.api

import retrofit2.Response

/**
 * Created on 2019-06-12.
 */
class ApiResponse<T> {

    val code: Int
    val body: T?
    val message: String?

    val isSuccessful: Boolean
        get() = code in 200..300

    private val isError: Boolean


    constructor(error: Throwable) {
        code = 500
        body = null
        message = error.message
        isError = true
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            message = response.message()
            body = response.body()
            isError = false
        } else {
            body = null
            isError = true
            var errorMessage: String? = null
            errorMessage = response.errorBody()?.toString()?.apply {
                if (isNullOrEmpty() || trim { it <= ' ' }.isEmpty()) {
                    response.message()
                }
            }
            message = errorMessage
        }
    }
}