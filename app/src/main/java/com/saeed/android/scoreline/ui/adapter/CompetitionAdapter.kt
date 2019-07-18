package com.saeed.android.scoreline.ui.adapter

import android.view.View
import com.saeed.android.scoreline.R
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Resource
import com.saeed.android.scoreline.ui.viewholder.BaseViewHolder
import com.saeed.android.scoreline.ui.viewholder.CompetitionViewHolder


/**
 * Created by Saeed on 2019-07-19.
 */
class CompetitionAdapter(private val delegate: BaseViewHolder.Delegate) : BaseAdapter() {

    override fun getLayoutRes(): Int {
        return R.layout.competition_list_item
    }

    override fun createViewHolder(view: View): BaseViewHolder {
        return CompetitionViewHolder(view, delegate)
    }

    fun addCompetitions(competitionResource: Resource<List<Competition>>) {
        competitionResource.data?.let {
            addData(it)
            notifyDataSetChanged()
        }
    }

}