package com.saeed.android.scoreline.ui.competition

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.saeed.android.scoreline.R
import com.saeed.android.scoreline.databinding.FragmentCompetitionsBinding
import com.saeed.android.scoreline.extension.viewModel
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Status.*
import com.saeed.android.scoreline.ui.BaseFragment
import com.saeed.android.scoreline.ui.adapter.CompetitionAdapter
import com.saeed.android.scoreline.ui.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_competitions.*
import timber.log.Timber

class CompetitionFragment : BaseFragment(), BaseViewHolder.Delegate {


    private lateinit var binding: FragmentCompetitionsBinding
    private val viewModel by lazy { viewModel(viewModelFactory, CompetitionViewModel::class) }

    override fun onItemClick(item: Any) {
        val competition = item as Competition
        Timber.d("Competition $competition")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_competitions, container, false)
        binding.viewModel = viewModel
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
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3
        }
        (competitions.layoutManager as? GridLayoutManager)?.spanCount = spanCount
    }


    private fun initUI() {
        competitions.adapter = CompetitionAdapter(this)
        viewModel.refreshCompetitions()
        viewModel.competitionListLiveData.observe(this, Observer {
            when (it.status) {
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
                    progress_circular.visibility = View.GONE
                    competitions.visibility = View.VISIBLE
                    //todo send error message
                }
            }
        })
        swipe_to_refresh.setOnRefreshListener {
            viewModel.refreshCompetitions(true)
        }
    }
}