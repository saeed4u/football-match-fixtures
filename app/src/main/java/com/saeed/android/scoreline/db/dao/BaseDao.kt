package com.saeed.android.scoreline.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy


/**
 * Created by Saeed on 2019-06-20.
 */
interface BaseDao<T> {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg data: T)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg data: List<T>)

}