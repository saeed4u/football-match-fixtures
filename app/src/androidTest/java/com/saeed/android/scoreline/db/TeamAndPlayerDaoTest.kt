package com.saeed.android.scoreline.db

import com.saeed.android.scoreline.LiveDataTestUtil
import com.saeed.android.scoreline.model.Player
import com.saeed.android.scoreline.model.Team
import com.saeed.android.scoreline.model.TeamAndPlayers
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test


/**
 * Created by Saeed on 2019-06-24.
 */
class TeamAndPlayerDaoTest : DbBaseTest() {

    private lateinit var teamAndPlayersList: ArrayList<TeamAndPlayers>

    override fun init() {
        createTeamAndPlayers()
    }

    private fun createTeamAndPlayers() {
        teamAndPlayersList = arrayListOf()
        var j = 1L
        for (i in 1..6) {
            val team = Team(
                i.toLong(),
                getDummyArea(i.toLong()),
                "Team $i",
                "Team $i",
                "Tla $i",
                "",
                "0202020202",
                "website $i",
                "email $i",
                2009 + i,
                "Accra, Ghana",
                "2019-06-24"
            )
            db.teamDao().insertAll(team)
            val players = arrayListOf<Player>()
            while (true) {
                val player = Player(j, i.toLong(), "Player $j", "$j", "Role $j")
                db.playerDao().insertAll(player)
                players.add(player)
                j++
                if (players.size == 6) {
                    break
                }
            }
            val teamAndPlayers = TeamAndPlayers()
            teamAndPlayers.team = team
            teamAndPlayers.players = players
            teamAndPlayersList.add(teamAndPlayers)
        }
    }

    @Test
    fun testCreateTeamAndPlayers() {
        val teamDao = db.teamDao()
        val teamAndPlayersListFromDB = teamDao.getTeams()
        MatcherAssert.assertThat(teamAndPlayersList.size, CoreMatchers.`is`(teamAndPlayersListFromDB.size))
        for (i in 0 until teamAndPlayersListFromDB.size) {
            val teamAnbPlayersFromDB = teamAndPlayersListFromDB[i]
            val teamAndPlayers = teamAndPlayersList[i]
            MatcherAssert.assertThat(teamAndPlayers.team, CoreMatchers.`is`(teamAnbPlayersFromDB.team))
            MatcherAssert.assertThat(teamAndPlayers.players.size, CoreMatchers.`is`(teamAnbPlayersFromDB.players.size))
        }
    }

    @Test
    fun testDeleteTeamAndPlayers(){
        createTeamAndPlayers()
        db.teamDao().deleteAll()
        MatcherAssert.assertThat(0,CoreMatchers.`is`(db.teamDao().getTeams().size))
        MatcherAssert.assertThat(0, CoreMatchers.`is`(LiveDataTestUtil.getValue(db.playerDao().getAllPlayers()).size))
    }
}