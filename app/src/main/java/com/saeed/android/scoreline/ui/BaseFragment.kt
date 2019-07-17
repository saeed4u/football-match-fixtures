package com.saeed.android.scoreline.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.saeed.android.scoreline.ui.dashboard.DashboardFragment
import com.saeed.android.scoreline.ui.dashboard.DashboardViewModel
import com.saeed.android.scoreline.ui.home.HomeFragment
import com.saeed.android.scoreline.ui.home.HomeViewModel
import com.saeed.android.scoreline.ui.notifications.NotificationsFragment
import com.saeed.android.scoreline.ui.notifications.NotificationsViewModel
import com.saeed.android.scoreline.util.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


/**
 * Created by Saeed on 2019-07-17.
 */
abstract class BaseFragment : DaggerFragment() {

    var viewModel: ViewModel? = null


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = when (this) {
            is DashboardFragment -> ViewModelProviders.of(this, viewModelFactory).get(
                DashboardViewModel::class.java
            )
            is HomeFragment -> ViewModelProviders.of(
                this,
                viewModelFactory
            ).get(HomeViewModel::class.java)
            is NotificationsFragment -> ViewModelProviders.of(this, viewModelFactory).get(
                NotificationsViewModel::class.java
            )
            else -> null
        }
    }

}