package com.saeed.android.scoreline.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.saeed.android.scoreline.R
import com.saeed.android.scoreline.databinding.FragmentHomeBinding
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Status.*
import com.saeed.android.scoreline.ui.BaseFragment
import com.saeed.android.scoreline.ui.adapter.CompetitionAdapter
import com.saeed.android.scoreline.ui.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : BaseFragment(), BaseViewHolder.Delegate {
    override fun onItemClick(item: Any) {
        val competition = item as Competition
        Timber.d("Competition $competition")
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel as HomeViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onResume() {
        super.onResume()
        val orientation = resources.configuration.orientation
        var spanCount = 2
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            spanCount = 3
        }
        (competitions.layoutManager as? GridLayoutManager)?.spanCount = spanCount
    }


    private fun initUI() {
        competitions.adapter = CompetitionAdapter(this)
        (viewModel as HomeViewModel).refreshCompetitions()
        (viewModel as HomeViewModel).competitionListLiveData.observe(this, Observer{
            when(it.status){
                LOADING -> {
                    swipe_to_refresh.isRefreshing = false
                    progress_circular.visibility = View.VISIBLE
                    competitions.visibility = View.GONE
                }
                SUCCESS -> {
                    progress_circular.visibility = View.GONE
                    competitions.visibility = View.VISIBLE
                }
                ERROR -> {

                }
            }
        })
        swipe_to_refresh.setOnRefreshListener {
            (viewModel as HomeViewModel).refreshCompetitions(true)
        }
    }
}