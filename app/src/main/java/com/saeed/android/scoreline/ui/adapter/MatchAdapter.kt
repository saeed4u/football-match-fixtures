package com.saeed.android.scoreline.ui.adapter

import android.view.View
import com.saeed.android.scoreline.R
import com.saeed.android.scoreline.model.Match
import com.saeed.android.scoreline.model.Resource
import com.saeed.android.scoreline.ui.viewholder.BaseViewHolder
import com.saeed.android.scoreline.ui.viewholder.MatchViewHolder


/**
 * Created by Saeed on 2019-07-26.
 */
class MatchAdapter(private val delegate: BaseViewHolder.Delegate) : BaseAdapter() {
    override fun getLayoutRes(): Int {
        return R.layout.match_list_item
    }

    override fun createViewHolder(view: View): BaseViewHolder {
        return MatchViewHolder(view, delegate)
    }

    fun addMatches(matches: Resource<List<Match>>){
        matches.data?.let {
            addData(it)
            notifyDataSetChanged()
        }
    }
}