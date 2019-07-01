package com.saeed.android.scoreline.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.saeed.android.scoreline.model.*
import org.junit.After
import org.junit.Before
import org.junit.Rule


/**
 * Created by Saeed on 2019-06-21.
 */
abstract class DbBaseTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    lateinit var db: AppDatabase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java)
            .build()
        init()
    }

    @After
    fun closeDb() {
        db.close()
    }

    abstract fun init()

    fun getDummyCompetition(id: Long): Competition {
        return Competition(
            id, getDummyArea(id), "Competition $id", "Plan $id", getDummySeason(id),
            3, "2019-06-21 17:08"
        )
    }

    fun getDummySeason(id: Long, startDate: String = "2019-06-21", endDate: String = "2019-06-29"): Season {
        return Season(id, startDate, endDate)
    }

    fun getDummyArea(id: Long, name: String = "Area for $id"): Area {
        return Area(id, name)
    }

    fun getDummyMatchTeam(id: Long, name: String = "MatchTeam $id"): MatchTeam {
        return MatchTeam(id, name)
    }

    fun getDummyScore(winner: String,duration: String = "90 mins"): Score{
        return Score(winner,duration,getDummySubScore(1,1),getDummySubScore(1,0))
    }

    private fun getDummySubScore(homeTeam: Long, awayTeam: Long): SubScore{
        return SubScore(homeTeam,awayTeam)
    }

}