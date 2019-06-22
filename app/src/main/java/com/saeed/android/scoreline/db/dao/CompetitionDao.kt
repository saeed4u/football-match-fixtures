package com.saeed.android.scoreline.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.saeed.android.scoreline.model.Competition


/**
 * Created by Saeed on 2019-06-20.
 */
@Dao
interface CompetitionDao : BaseDao<Competition> {

    @Query("SELECT * FROM competition")
    fun getCompetitions(): LiveData<List<Competition>>

    @Query("SELECT * FROM competition where id=:id")
    fun getCompetition(id: Long): Competition

    @Query("DELETE FROM competition")
    fun deleteAll()

}