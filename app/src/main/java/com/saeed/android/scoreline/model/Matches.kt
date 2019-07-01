@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.saeed.android.scoreline.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore


/**
 * Created by Saeed on 2019-06-18.
 */

data class Area(var id: Long? = 0L, var name: String? = "")

data class Season(
    var id: Long? = 0L,
    var startDate: String? = "",
    var endDate: String? = "",
    var currentMatchday: Long? = 0L,
    var availableStages: List<String>? = arrayListOf()
)

@Entity(primaryKeys = ["id"])
data class Competition(
    val id: Long? = 0L,
    var area: Area? = Area(),
    var name: String? = "",
    var plan: String? = "",
    var currentSeason: Season? = Season(),
    var numberOfAvailableSeasons: Long? = 0L,
    var lastUpdated: String? = ""
)

data class SubScore(var homeTeam: Long? = 0L, var awayTeam: Long? = 0L)

data class Score(
    var winner: String? = "",
    var duration: String? = "",
    var fullTime: SubScore? = SubScore(),
    var halfTime: SubScore? = SubScore()
)

data class MatchTeam(var id: Long? = 0L, var name: String? = "")

@Entity(primaryKeys = ["id"])
data class Match(
    var id: Long? = 0L,
    var competition: Competition? = Competition(),
    var season: Season? = Season(),
    var utcDate: String? = "",
    var status: String? = "",
    var attendance: Long? = 0L,
    var stage: String? = "",
    var group: String? = "",
    var lastUpdated: String? = "",
    var homeTeam: MatchTeam? = MatchTeam(),
    var awayTeam: MatchTeam? = MatchTeam(),
    var score: Score? = Score()
)

@Entity(
    primaryKeys = ["id"],
    foreignKeys = [ForeignKey(
        entity = Team::class,
        parentColumns = ["id"],
        childColumns = ["teamId"],
        onDelete = CASCADE
    )]
)
data class Player(
    var id: Long? = 0L,
    var teamId: Long? = 0,
    var name: String? = "",
    var position: String? = "",
    var role: String? = ""
)

@Entity(primaryKeys = ["id"])
data class Team(
    var id: Long? = 0,
    var area: Area? = Area(),
    var name: String? = "",
    var shortName: String? = "",
    var tla: String? = "",
    var crestUrl: String? = "",
    var phone: String? = "",
    var website: String? = "",
    var email: String? = "",
    var founded: Int? = 0,
    var venue: String? ="",
    var lastUpdated: String?="",
    @Ignore var squad: List<Player> = arrayListOf()
) {
    constructor(
        id: Long = 0,
        area: Area = Area(),
        name: String = "",
        shortName: String = "",
        tla: String = "",
        crestUrl: String = "",
        phone: String = "",
        website: String = "",
        email: String = "",
        founded: Int = 0,
        venue: String,
        lastUpdated: String
    ) : this(id, area, name, shortName, tla, crestUrl, phone, website, email, founded, venue, lastUpdated, listOf())
}
