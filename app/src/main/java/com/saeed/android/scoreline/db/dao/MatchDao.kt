package com.saeed.android.scoreline.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.saeed.android.scoreline.model.Match
import io.reactivex.Flowable


/**
 * Created by Saeed on 2019-06-20.
 */
@Dao
interface MatchDao: BaseDao<Match> {

    @Query("SELECT * FROM `match`")
    fun getAllMatches(): Flowable<List<Match>>

    @Query("SELECT * FROM `match` WHERE id=:matchId")
    fun getMatch(matchId: Long): Flowable<Match>

    @Query("DELETE FROM `match`")
    fun deleteAll()

}