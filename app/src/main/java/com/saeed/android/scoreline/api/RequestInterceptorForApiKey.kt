package com.saeed.android.scoreline.api

import com.saeed.android.scoreline.util.FOOTBALL_SERVICE_API_KEY
import com.saeed.android.scoreline.util.FOOTBALL_SERVICE_API_KEY_HEADER
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created by Saeed on 2019-06-20.
 */
class RequestInterceptorForApiKey() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val headers = originalRequest.headers()

        val requestBuilder = originalRequest.newBuilder()
            .headers(headers.newBuilder().add(FOOTBALL_SERVICE_API_KEY_HEADER, FOOTBALL_SERVICE_API_KEY).build())
        return chain.proceed(requestBuilder.build())
    }
}