package com.saeed.android.scoreline.model


/**
 * Created by Saeed on 2019-09-08.
 */
data class MatchRequest(
    val refresh: Boolean = false,
    var teamId: Long = 0,
    var dateFrom: String = "",
    var dateTo: String = ""
)