package com.saeed.android.scoreline.di.module

import com.saeed.android.scoreline.di.module.sub.MainActivityFragmentModule
import com.saeed.android.scoreline.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Saeed on 2019-06-14.
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    abstract fun providesMainActivity(): MainActivity

}