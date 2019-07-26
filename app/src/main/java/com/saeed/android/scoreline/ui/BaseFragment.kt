package com.saeed.android.scoreline.ui

import android.os.Bundle
import com.saeed.android.scoreline.util.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


/**
 * Created by Saeed on 2019-07-17.
 */
abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}