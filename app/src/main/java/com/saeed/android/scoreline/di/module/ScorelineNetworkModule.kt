package com.saeed.android.scoreline.di.module

import com.saeed.android.scoreline.api.RequestInterceptorForApiKey
import com.saeed.android.scoreline.api.service.FootballDataService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
            .baseUrl("")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideFootballDataService(retrofit: Retrofit): FootballDataService {
        return retrofit.create(FootballDataService::class.java)
    }
}