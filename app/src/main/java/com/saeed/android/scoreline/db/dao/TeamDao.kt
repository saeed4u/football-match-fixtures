package com.saeed.android.scoreline.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.saeed.android.scoreline.model.Team
import com.saeed.android.scoreline.model.TeamAndPlayers


/**
 * Created by Saeed on 2019-06-20.
 */
@Dao
interface TeamDao : BaseDao<Team> {

    @Transaction
    @Query("SELECT * FROM team")
    fun getTeams(): LiveData<TeamAndPlayers>

    @Query("DELETE FROM team")
    fun deleteAll();

}