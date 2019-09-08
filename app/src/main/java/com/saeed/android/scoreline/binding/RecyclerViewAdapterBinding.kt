package com.saeed.android.scoreline.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saeed.android.scoreline.extension.bindResource
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Match
import com.saeed.android.scoreline.model.Resource
import com.saeed.android.scoreline.ui.adapter.CompetitionAdapter
import com.saeed.android.scoreline.ui.adapter.MatchAdapter

/**
 * Created by Saeed on 2019-07-19.
 */

@BindingAdapter("adapterCompetitions")
fun bindCompetitionAdapter(recyclerView: RecyclerView, competitions: Resource<List<Competition>>?) {
    competitions?.let {
        recyclerView.bindResource(it) {
            val adapter = recyclerView.adapter as? CompetitionAdapter
            adapter?.addCompetitions(it)
        }
    }

}

@BindingAdapter("adapterMatches")
fun bindMatchesAdapter(recyclerView: RecyclerView, matches: Resource<List<Match>>?) {
    matches?.let {
        recyclerView.bindResource(it) {
            val adapter = recyclerView.adapter as? MatchAdapter
            adapter?.addMatches(it)
        }
    }
}