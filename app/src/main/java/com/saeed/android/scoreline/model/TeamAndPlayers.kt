package com.saeed.android.scoreline.model

import androidx.room.Embedded
import androidx.room.Relation


/**
 * Created by Saeed on 2019-06-20.
 */
class TeamAndPlayers {

    @Embedded
    var team: Team? = null

    @Relation(parentColumn = "id", entityColumn = "teamId")
    var players: List<Player> = arrayListOf()

}