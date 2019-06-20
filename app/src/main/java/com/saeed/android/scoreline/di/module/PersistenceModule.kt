package com.saeed.android.scoreline.di.module

import androidx.room.Room
import com.saeed.android.scoreline.ScorelineApp
import com.saeed.android.scoreline.db.AppDatabase
import com.saeed.android.scoreline.db.dao.CompetitionDao
import com.saeed.android.scoreline.db.dao.MatchDao
import com.saeed.android.scoreline.db.dao.PlayerDao
import com.saeed.android.scoreline.db.dao.TeamDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Saeed on 2019-06-14.
 */
@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun databaseProvider(application: ScorelineApp): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "score-line").build()
    }

    @Provides
    @Singleton
    fun teamDaoProvider(db: AppDatabase): TeamDao {
        return db.teamDao()
    }

    @Provides
    @Singleton
    fun competitionDaoProvider(db: AppDatabase): CompetitionDao {
        return db.competitionDao()
    }

    @Provides
    @Singleton
    fun matchDaoProvider(db: AppDatabase): MatchDao {
        return db.matchDao()
    }

    @Provides
    @Singleton
    fun serviceAreaProvider(db: AppDatabase): PlayerDao {
        return db.playerDao()
    }

}