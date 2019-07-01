package com.saeed.android.scoreline.model


/**
 * Created by Saeed on 2019-07-01.
 */
data class CompetitionResponse(val count: Int,val competitions: List<Competition>)

data class MatchResponse(val count: Int, val competition: Competition,val matches: List<Match>)

data class TeamsResponse(val count: Int, val competition: Competition, val season: Season, val teams: List<Team>)