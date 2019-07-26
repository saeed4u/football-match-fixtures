package com.saeed.android.scoreline.ui.viewholder

import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import com.saeed.android.scoreline.R
import com.saeed.android.scoreline.model.Match
import kotlinx.android.synthetic.main.competition_list_item.view.last_updated
import kotlinx.android.synthetic.main.match_list_item.view.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Saeed on 2019-07-26.
 */
class MatchViewHolder(view: View, private val delegate: Delegate) : BaseViewHolder(view){
    private lateinit var match: Match

    override fun bindData(data: Any) {
        if (data is Match) {
            match= data
            initItem()
        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(match)
    }

    private fun initItem(){
        itemView.run {
            val matchName = "${match.homeTeam?.name} vs ${match.awayTeam?.name}"
            match_name.text = matchName
            match_status.text = match.status
            this.findViewById<TextView>(R.id.competition_name).text = match.competition?.name
            group_name.text = match.season?.currentMatchday.toString()
            val utcDate = match.utcDate
            Timber.d("utcDate  $utcDate")
            val dateTimePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            val dateFormat = SimpleDateFormat(dateTimePattern, Locale.getDefault())
            val relativeTime = DateUtils.getRelativeTimeSpanString(dateFormat.parse(utcDate).time,
                Date().time,
                DateUtils.MINUTE_IN_MILLIS).toString()
            last_updated.text = relativeTime
        }
    }
}