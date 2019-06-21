package com.saeed.android.scoreline.api.adapter

import androidx.lifecycle.LiveData
import com.saeed.android.scoreline.api.ApiResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * An adapter factory that creates a LiveDataCallAdapter
 * Created by Saeed on 2019-06-21.
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): LiveDataCallAdapter<*>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val parameterType = getParameterUpperBound(0, returnType as ParameterizedType) as? ParameterizedType
            ?: throw IllegalArgumentException("Return type not parameterized")
        val rawType = getRawType(parameterType)
        if (rawType != ApiResponse::class.java) {
            throw IllegalArgumentException("Type must be a ApiResponse")
        }
        val body = getParameterUpperBound(0, parameterType)
        return LiveDataCallAdapter<Type>(body)
    }
}