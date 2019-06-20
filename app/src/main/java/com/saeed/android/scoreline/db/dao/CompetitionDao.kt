package com.saeed.android.scoreline.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saeed.android.scoreline.model.Competition
import io.reactivex.Flowable


/**
 * Created by Saeed on 2019-06-20.
 */
@Dao
interface CompetitionDao: BaseDao<Competition> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCompetions(competitions: List<Competition>)

    @Query("SELECT * FROM competition")
    fun getCompetitions(): Flowable<List<Competition>>

    @Query("DELETE FROM competition")
    fun deleteAll()

}