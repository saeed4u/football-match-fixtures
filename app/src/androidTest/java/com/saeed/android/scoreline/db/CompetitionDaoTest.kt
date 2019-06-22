package com.saeed.android.scoreline.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.saeed.android.scoreline.LiveDataTestUtil
import com.saeed.android.scoreline.model.Area
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Season
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber


/**
 * Created by Saeed on 2019-06-21.
 */

@RunWith(AndroidJUnit4::class)
class CompetitionDaoTest : DbBaseTest() {

    private lateinit var competitions: ArrayList<Competition>

    override fun init() {
        Timber.d("Init.....")
        createCompetition()
    }


    @Test
    fun testInsertCompetition() {
        val compFromDb = LiveDataTestUtil.getValue(db.competitionDao().getCompetitions())
        MatcherAssert.assertThat(compFromDb.size, CoreMatchers.`is`(competitions.size))
        for (i in 0 until competitions.size) {
            val competition = compFromDb[i]
            val competition_ = competitions[i]
            MatcherAssert.assertThat(competition.id, CoreMatchers.`is`(competition_.id))
            MatcherAssert.assertThat(competition.name, CoreMatchers.`is`(competition_.name))
            MatcherAssert.assertThat(competition.area, CoreMatchers.`is`(competition_.area))
            MatcherAssert.assertThat(competition.currentSeason, CoreMatchers.`is`(competition_.currentSeason))
            MatcherAssert.assertThat(competition.plan, CoreMatchers.`is`(competition_.plan))
            MatcherAssert.assertThat(competition.lastUpdated, CoreMatchers.`is`(competition_.lastUpdated))
        }
    }

    @Test
    fun testUpdateCompetition() {
        val competitionDao = db.competitionDao()
        val compFromDb = LiveDataTestUtil.getValue(competitionDao.getCompetitions())
        val firstCompetition = compFromDb[0]

        MatcherAssert.assertThat(firstCompetition.name, CoreMatchers.`is`(competitions[0].name))

        firstCompetition.name = "Updated name"
        competitionDao.update(firstCompetition)

        val updatedCompetition = competitionDao.getCompetition(firstCompetition.id)

        MatcherAssert.assertThat(firstCompetition.name, CoreMatchers.`is`(updatedCompetition.name))

    }

    @Test
    fun testDeleteCompetitions() {
        val competitionDao = db.competitionDao()
        val compFromDb = LiveDataTestUtil.getValue(competitionDao.getCompetitions())
        MatcherAssert.assertThat(compFromDb.size, CoreMatchers.`is`(competitions.size))

        competitionDao.deleteAll()

        val newCompFromDb = LiveDataTestUtil.getValue(competitionDao.getCompetitions())

        MatcherAssert.assertThat(newCompFromDb.size, CoreMatchers.`is`(0))
    }

    private fun createCompetition() {
        competitions = arrayListOf()
        for (i in 1..6) {
            competitions.add(
                Competition(
                    i.toLong(), Area(i.toLong(), "Area $i"),
                    "Comp $i", "Plan $i", Season(i.toLong()),
                    3, "2019-06-21 17:08"
                )
            )
        }

        db.competitionDao().insertAll(
            competitions[0],
            competitions[1],
            competitions[2],
            competitions[3],
            competitions[4],
            competitions[5]
        )
    }

}