package com.saeed.android.scoreline.di.module.sub

import com.saeed.android.scoreline.ui.competition.CompetitionFragment
import com.saeed.android.scoreline.ui.dashboard.DashboardFragment
import com.saeed.android.scoreline.ui.matches.MatchesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Saeed on 2019-06-14.
 */
@Module
abstract class MainActivityFragmentModule {

    @ContributesAndroidInjector
    abstract fun providesDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun providesHomeFragment(): CompetitionFragment


    @ContributesAndroidInjector
    abstract fun providesMatchesFragment(): MatchesFragment

}