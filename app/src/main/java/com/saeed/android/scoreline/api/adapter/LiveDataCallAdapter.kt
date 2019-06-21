package com.saeed.android.scoreline.api.adapter

import androidx.lifecycle.LiveData
import com.saeed.android.scoreline.api.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean


/**
 * Created by Saeed on 2019-06-21.
 * An adapter to convert API responses to LiveData<ApiResponse<R>>
 */
class LiveDataCallAdapter<T>(private val type: Type) : CallAdapter<T, LiveData<ApiResponse<T>>> {


    override fun adapt(call: Call<T>): LiveData<ApiResponse<T>> {
        return object : LiveData<ApiResponse<T>>() {
            var startedCheck = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (startedCheck.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            postValue(ApiResponse(t))
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            postValue(ApiResponse(response))
                        }

                    })
                }
            }
        }
    }

    override fun responseType(): Type {
        return type
    }
}