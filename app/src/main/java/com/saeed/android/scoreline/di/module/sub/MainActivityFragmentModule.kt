package com.saeed.android.scoreline.di.module.sub

import com.saeed.android.scoreline.ui.dashboard.DashboardFragment
import com.saeed.android.scoreline.ui.home.CompetitionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Saeed on 2019-06-14.
 */
@Module
abstract class MainActivityFragmentModule {

    @ContributesAndroidInjector(modules = [DashboardFragmentModule::class])
    abstract fun providesDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun providesHomeFragment(): CompetitionFragment

}