package com.saeed.android.scoreline.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.saeed.android.scoreline.R
import com.saeed.android.scoreline.databinding.FragmentMatchesBinding
import com.saeed.android.scoreline.extension.viewModel
import com.saeed.android.scoreline.model.Match
import com.saeed.android.scoreline.model.Status
import com.saeed.android.scoreline.ui.BaseFragment
import com.saeed.android.scoreline.ui.adapter.MatchAdapter
import com.saeed.android.scoreline.ui.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_competitions.progress_circular
import kotlinx.android.synthetic.main.fragment_competitions.swipe_to_refresh
import kotlinx.android.synthetic.main.fragment_matches.*
import ru.slybeaver.slycalendarview.SlyCalendarDialog
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Saeed on 2019-07-26.
 */
class MatchesFragment : BaseFragment(), BaseViewHolder.Delegate {
    override fun onItemClick(item: Any) {
        val match = item as Match
        Timber.d("Match $match")
    }

    override fun initUI() {
        matches.adapter = MatchAdapter(this)
        viewModel.refreshMatches()
        viewModel.matchesListLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    setLoadingStatus()
                }
                Status.SUCCESS -> {
                    setLoadingDoneStatus()
                }
                Status.ERROR -> {
                    progress_circular.visibility = View.GONE
                    matches.visibility = View.VISIBLE
                    setLoadingDoneStatus()
                }
            }
        })
        swipe_to_refresh.setOnRefreshListener {
            viewModel.refreshMatches(true)
        }

        filter_matches.setOnClickListener {
            SlyCalendarDialog()
                .setSingle(false)
                .setCallback(object : SlyCalendarDialog.Callback {
                    override fun onDataSelected(
                        firstDate: Calendar?,
                        secondDate: Calendar?,
                        hours: Int,
                        minutes: Int
                    ) {
                        firstDate?.let {
                            val dateFormatter = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
                            val from = dateFormatter.format(it)
                            var to = from
                            if (secondDate!=null){
                                to = dateFormatter.format(secondDate)
                            }
                        }
                    }

                    override fun onCancelled() {
                    }

                }).show(fragmentManager, "MatchesDialog")
        }
    }

    private fun setLoadingDoneStatus() {
        progress_circular.visibility = View.GONE
        matches.visibility = View.VISIBLE
        filter_matches.animate().scaleX(1F)
            .scaleY(1F)
            .start()
    }

    private fun setLoadingStatus() {
        swipe_to_refresh.isRefreshing = false
        progress_circular.visibility = View.VISIBLE
        matches.visibility = View.GONE
        filter_matches.animate().scaleX(0F)
            .scaleY(0F)
            .start()
    }


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

}