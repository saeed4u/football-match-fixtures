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


    constructor(error: Throwable){
        code = 500
        body = null
        message = error.message
        isError = true
    }

    constructor(response: Response<T>){
        code = response.code()
        if (response.isSuccessful){
            this.message = response.message()
            this.body = response.body()
            isError = false
        }else{
            this.body = null
            this.isError = true
            var errorMessage: String? = null
            response.errorBody()?.let {
                try{
                    errorMessage = response.errorBody()!!.toString()
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
            errorMessage?.apply {
                if (isNullOrEmpty() || trim { it <= ' ' }.isEmpty()) {
                    errorMessage = response.message()
                }
            }
            message = errorMessage
        }
    }
}