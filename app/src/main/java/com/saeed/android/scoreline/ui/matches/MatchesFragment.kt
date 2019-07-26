package com.saeed.android.scoreline.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.saeed.android.scoreline.R
import com.saeed.android.scoreline.databinding.FragmentMatchesBinding
import com.saeed.android.scoreline.extension.viewModel
import com.saeed.android.scoreline.ui.BaseFragment


/**
 * Created by Saeed on 2019-07-26.
 */
class MatchesFragment : BaseFragment(){


    private val viewModel by lazy { viewModel(viewModelFactory, MatchesViewModel::class) }

    private lateinit var binding: FragmentMatchesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_matches, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}