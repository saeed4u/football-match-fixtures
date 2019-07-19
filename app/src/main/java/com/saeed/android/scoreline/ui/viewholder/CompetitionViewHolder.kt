package com.saeed.android.scoreline.ui.viewholder

import android.text.format.DateUtils
import android.view.View
import com.saeed.android.scoreline.model.Competition
import kotlinx.android.synthetic.main.competition_list_item.view.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


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
            val lastUpdated = competition.lastUpdated
            Timber.d("Lastupdated at $lastUpdated")
            val dateTimePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            val dateFormat = SimpleDateFormat(dateTimePattern, Locale.getDefault())
            val relativeTime = DateUtils.getRelativeTimeSpanString(dateFormat.parse(lastUpdated).time,Date().time,DateUtils.MINUTE_IN_MILLIS).toString()
            last_updated.text = relativeTime
        }
    }
}