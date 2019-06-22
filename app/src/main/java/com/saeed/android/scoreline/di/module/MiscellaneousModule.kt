package com.saeed.android.scoreline.di.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Saeed on 2019-06-14.
 */
@Module
class MiscellaneousModule {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder().build()
    }

}