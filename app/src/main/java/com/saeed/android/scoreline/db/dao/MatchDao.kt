package com.saeed.android.scoreline.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.saeed.android.scoreline.model.Match


/**
 * Created by Saeed on 2019-06-20.
 */
@Dao
interface MatchDao : BaseDao<Match> {

    @Query("SELECT * FROM `match`")
    fun getAllMatches(): LiveData<List<Match>>

    @Query("SELECT * FROM `match` WHERE teamId = :teamId")
    fun getTeamMatches(teamId: Long): LiveData<List<Match>>

    @Query("SELECT * FROM `match` WHERE competitionId = :compId")
    fun getCompetitionMatches(compId: Long): LiveData<List<Match>>

    @Query("SELECT * FROM `match` WHERE id=:matchId")
    fun getMatch(matchId: Long): Match

    @Query("DELETE FROM `match`")
    fun deleteAll()

}