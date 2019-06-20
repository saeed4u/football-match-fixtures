package com.saeed.android.scoreline.di.module

import androidx.lifecycle.ViewModelProvider
import com.saeed.android.scoreline.util.ViewModelFactory
import dagger.Binds
import dagger.Module


/**
 * Created by Saeed on 2019-06-14.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}