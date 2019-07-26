package com.saeed.android.scoreline.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.saeed.android.scoreline.util.ViewModelFactory
import kotlin.reflect.KClass


/**
 * Created by Saeed on 2019-07-26.
 */

fun <T : ViewModel> Fragment.viewModel(factory: ViewModelFactory, viewModel: KClass<T>): T {
    return ViewModelProviders.of(this, factory).get(viewModel.java)
}