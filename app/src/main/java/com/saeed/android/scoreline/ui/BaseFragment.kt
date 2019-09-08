package com.saeed.android.scoreline.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.saeed.android.scoreline.util.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


/**
 * Created by Saeed on 2019-07-17.
 */
abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected var baseActivity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        baseActivity = context as BaseActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    abstract fun initUI()

}