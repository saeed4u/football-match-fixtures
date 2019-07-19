package com.saeed.android.scoreline.di.module

import com.saeed.android.scoreline.api.RequestInterceptorForApiKey
import com.saeed.android.scoreline.api.adapter.LiveDataCallAdapterFactory
import com.saeed.android.scoreline.api.service.FootballDataService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Saeed on 2019-06-14.
 */
@Module
class ScorelineNetworkModule {


    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(RequestInterceptorForApiKey())
            .connectTimeout(1, TimeUnit.MINUTES).retryOnConnectionFailure(true).build()
    }


    @Provides
    @Singleton
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.football-data.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideFootballDataService(retrofit: Retrofit): FootballDataService {
        return retrofit.create(FootballDataService::class.java)
    }
}