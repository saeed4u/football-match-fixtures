package com.saeed.android.scoreline.di.module.sub

import com.saeed.android.scoreline.ui.matches.MatchesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Saeed on 2019-07-26.
 */
@Module
abstract class DashboardFragmentModule {

    @ContributesAndroidInjector
    abstract fun providesMatchesFragment(): MatchesFragment

}