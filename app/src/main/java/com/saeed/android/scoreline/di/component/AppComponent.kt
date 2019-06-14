package com.saeed.android.scoreline.di.component

import com.saeed.android.scoreline.ScorelineApp
import com.saeed.android.scoreline.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by Saeed on 2019-06-14.
 */
@Singleton
@Component(
    modules = [
        (ActivityBindingModule::class), (MiscellaneousModule::class),
        (PersistenceModule::class), (ScorelineNetworkModule::class),
        (ViewModelModule::class), (AndroidSupportInjectionModule::class)
    ]
)
interface AppComponent : AndroidInjector<ScorelineApp> {

    override fun inject(instance: ScorelineApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(scorelineApp: ScorelineApp): Builder

        fun build(): AppComponent
    }
}