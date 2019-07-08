package com.saeed.android.scoreline.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saeed.android.scoreline.api.ApiResponse
import retrofit2.Response


/**
 * Created by Saeed on 2019-07-02.
 */
object Util {

    fun<T: Any> createSuccessCall(data: T) = createCall(Response.success(data))

    private fun<T: Any>  createCall(response: Response<T>) = MutableLiveData<ApiResponse<T>>().apply {
                value = ApiResponse(response)
    } as LiveData<ApiResponse<T>>

}