package com.saeed.android.scoreline

import com.saeed.android.scoreline.di.component.AppComponent
import com.saeed.android.scoreline.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


/**
 * Created by Saeed on 2019-06-14.
 */
class ScorelineApp: DaggerApplication() {

    private val appComponent: AppComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}