package com.saeed.android.scoreline.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saeed.android.scoreline.db.dao.CompetitionDao
import com.saeed.android.scoreline.db.dao.MatchDao
import com.saeed.android.scoreline.db.dao.PlayerDao
import com.saeed.android.scoreline.db.dao.TeamDao
import com.saeed.android.scoreline.db.typeconverter.Converter
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Match
import com.saeed.android.scoreline.model.Player
import com.saeed.android.scoreline.model.Team


/**
 * Created by Saeed on 2019-06-20.
 */
@Database(version = 1, entities = [(Competition::class), (Match::class), (Player::class), (Team::class)])
@TypeConverters(value = [Converter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao

    abstract fun matchDao(): MatchDao

    abstract fun competitionDao(): CompetitionDao

    abstract fun playerDao(): PlayerDao
}