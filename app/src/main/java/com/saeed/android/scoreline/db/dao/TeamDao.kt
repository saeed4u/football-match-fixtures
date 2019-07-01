package com.saeed.android.scoreline.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.saeed.android.scoreline.model.Team
import com.saeed.android.scoreline.model.TeamAndPlayers


/**
 * Created by Saeed on 2019-06-20.
 */
@Dao
interface TeamDao : BaseDao<Team> {

    @Query("SELECT * FROM team")
    fun getTeams(): List<TeamAndPlayers>

    @Query("DELETE FROM team")
    fun deleteAll()

}