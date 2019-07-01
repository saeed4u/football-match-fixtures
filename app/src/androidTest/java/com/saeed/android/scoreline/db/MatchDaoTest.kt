package com.saeed.android.scoreline.db

import com.saeed.android.scoreline.LiveDataTestUtil
import com.saeed.android.scoreline.model.Match
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test


/**
 * Created by Saeed on 2019-06-24.
 */
class MatchDaoTest : DbBaseTest() {

    private lateinit var matches: ArrayList<Match>

    override fun init() {
        createMatches()
    }

    @Test
    fun testInsertMatches() {
        val matchDao = db.matchDao()
        val matchesFromDB = LiveDataTestUtil.getValue(matchDao.getAllMatches())
        MatcherAssert.assertThat(matches.size, CoreMatchers.`is`(matchesFromDB.size))
        for (i in 0 until matches.size) {
            val match = matches[i]
            val matchFromDb = matchesFromDB[i]
            MatcherAssert.assertThat(match, CoreMatchers.`is`(matchFromDb))
        }
    }

    @Test
    fun testUpdateMatch(){
        val matchDao = db.matchDao()
        val matchesFromDB = LiveDataTestUtil.getValue(matchDao.getAllMatches())

        val firstMatch = matchesFromDB[0]

        MatcherAssert.assertThat(firstMatch.attendance, CoreMatchers.`is`(matches[0].attendance))

        firstMatch.attendance = 200
        matchDao.update(firstMatch)

        val updatedMatch = matchDao.getMatch(firstMatch.id)

        MatcherAssert.assertThat(200, CoreMatchers.`is`(updatedMatch.attendance))
    }

    @Test
    fun testDeleteMatches(){
        val matchDao = db.matchDao()
        val matchesFromDB = LiveDataTestUtil.getValue(matchDao.getAllMatches())
        MatcherAssert.assertThat(matches.size, CoreMatchers.`is`(matchesFromDB.size))

        matchDao.deleteAll()

        val newMatchesFromDB = LiveDataTestUtil.getValue(matchDao.getAllMatches())

        MatcherAssert.assertThat(0, CoreMatchers.`is`(newMatchesFromDB.size))
    }

    private fun createMatches() {
        matches = arrayListOf()
        for (i in 1..6) {
            matches.add(
                Match(
                    i.toLong(),
                    getDummyCompetition(i.toLong()),
                    getDummySeason(i.toLong()),
                    "2019-06-24",
                    "NOT_PLAYED",
                    0,
                    "",
                    "",
                    "2019-06-24",
                    getDummyMatchTeam(i.toLong(), "Home team $i"),
                    getDummyMatchTeam(i.toLong(), "Away team $i"),
                    getDummyScore("Draw")
                )
            )
        }
        db.matchDao().insertAll(matches[0], matches[1], matches[2], matches[3], matches[4], matches[5])
    }

}