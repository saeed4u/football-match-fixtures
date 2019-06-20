package com.saeed.android.scoreline.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.saeed.android.scoreline.model.Player
import io.reactivex.Flowable


/**
 * Created by Saeed on 2019-06-20.
 */
@Dao
interface PlayerDao : BaseDao<Player> {

    @Query("SELECT * FROM player")
    fun getAllPlayers(): Flowable<List<Player>>

    @Query("SELECT * FROM player where id=:playerId")
    fun getPlayer(playerId: Long): Flowable<Player>

}