package com.saeed.android.scoreline.ui.matches

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
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
import timber.log.Timber

/**
 * Created by Saeed on 2019-07-26.
 */
class MatchesFragment : BaseFragment(), BaseViewHolder.Delegate{
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
                    swipe_to_refresh.isRefreshing = false
                    progress_circular.visibility = View.VISIBLE
                    matches.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    progress_circular.visibility = View.GONE
                    matches.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progress_circular.visibility = View.GONE
                    matches.visibility = View.VISIBLE
                    //todo send error message
                }
            }
        })
        swipe_to_refresh.setOnRefreshListener {
            viewModel.refreshMatches(true)
        }

        filter_matches.setOnClickListener {
           val datePicker = DatePickerDialog(baseActivity!!,object : DatePickerDialog.OnDateSetListener{
               override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
               }

           },2019,1,24)
            datePicker.show()
        }
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