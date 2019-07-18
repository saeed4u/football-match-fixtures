package com.saeed.android.scoreline.ui.adapter.viewholder

import android.view.View
import com.saeed.android.scoreline.model.Competition
import kotlinx.android.synthetic.main.competition_list_item.view.*


/**
 * Created by Saeed on 2019-07-19.
 */
class CompetitionViewHolder(view: View, private val delegate: Delegate) : BaseViewHolder(view) {

    private lateinit var competition: Competition

    override fun bindData(data: Any) {
        if (data is Competition) {
            competition = data
            initItem()
        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(competition)
    }

    private fun initItem() {
        itemView.run {
            competition_name.text = competition.name
            competition_area.text = competition.area?.name
            competition_plan.text = competition.plan
        }
    }
}