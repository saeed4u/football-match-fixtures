@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.saeed.android.scoreline.model

import androidx.room.Embedded
import androidx.room.Entity


/**
 * Created by Saeed on 2019-06-18.
 */

data class Area(var id: Long = 0L, var name: String = "")

data class Season(
    var id: Long = 0L,
    var startDate: String = "",
    var endDate: String = "",
    var currentMatchday: Long = 0L,
    var availableStages: List<String> = arrayListOf()
)

@Entity(primaryKeys = ["id"])
data class Competition(
    val id: Long = 0L, @Embedded var area: Area = Area(),
    var name: String = "",
    var plan: String = "",
    @Embedded var currentSeason: Season = Season(),
    var numberOfAvailableSeasons: Long = 0L,
    var lastUpdated: String = ""
)

data class Player(var id: Long = 0L, var name: String = "", var position: String = "", var shirtNumber: Long = 0L)

data class SubScore(var homeTeam: Long = 0L, var awayTeam: Long = 0L)

data class Score(
    var winner: String = "",
    var duration: String = "",
    var fullTime: SubScore = SubScore(),
    var halfTime: SubScore = SubScore()
)

data class Team(var id: Long = 0L, var name: String = "", var captain: Player = Player())

@Entity(primaryKeys = ["id"])
data class Match(
    var id: Long = 0L,
    @Embedded var competition: Competition = Competition(),
    @Embedded var season: Season = Season(),
    var utcDate: String = "",
    var status: String = "",
    var attendance: Long = 0L,
    var stage: String = "",
    var group: String = "",
    var lastUpdated: String = "",
    @Embedded var homeTeam: Team = Team(),
    @Embedded var awayTeam: Team = Team(),
    @Embedded var score: Score = Score()
)