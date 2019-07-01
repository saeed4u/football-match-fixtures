package com.saeed.android.scoreline.api

import com.saeed.android.scoreline.LiveDataTestUtil
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


/**
 * Created by Saeed on 2019-07-01.
 */
class FootballServiceTest : MockApiCall() {

    @Test
    fun testGetAllCompetitions() {
        enqueueResponse("competitions.json")
        val response = LiveDataTestUtil.getValue(footballDataService.getAllCompetitions())
        val competitionResponse = response.body
        assertThat(competitionResponse, CoreMatchers.not(CoreMatchers.nullValue()))
        assertThat(competitionResponse?.count, CoreMatchers.`is`(competitionResponse?.competitions?.size))
        val competition = competitionResponse?.competitions?.get(0)
        assertThat(2006, CoreMatchers.`is`(competition?.id))
        assertThat("WC Qualification", CoreMatchers.`is`(competition?.name))
    }

    @Test
    fun testGetTeamsInCompetitions() {
        enqueueResponse("teams.json")
        val response = LiveDataTestUtil.getValue(footballDataService.getTeamsInCompetition(2001))
        val teamResponse = response.body
        assertThat(teamResponse, CoreMatchers.not(CoreMatchers.nullValue()))
        assertThat(teamResponse?.count, CoreMatchers.`is`(teamResponse?.teams?.size))
        assertThat(2001, CoreMatchers.`is`(teamResponse?.competition?.id))
        val team = teamResponse?.teams?.get(0)
        assertThat(3, CoreMatchers.`is`(team?.id))
        assertThat("Bayer 04 Leverkusen", CoreMatchers.`is`(team?.name))

    }

    @Test
    fun testGetMatchesOfCompetition() {
        enqueueResponse("competition_matches.json")
        val response = LiveDataTestUtil.getValue(footballDataService.getMatchesOfCompetition(2001))
        val matchResponse = response.body
        assertThat(matchResponse, CoreMatchers.not(CoreMatchers.nullValue()))
        assertThat(matchResponse?.count, CoreMatchers.`is`(matchResponse?.matches?.size))
        assertThat(2001, CoreMatchers.`is`(matchResponse?.competition?.id))
        val match = matchResponse?.matches?.get(0)
        assertThat(266391, CoreMatchers.`is`(match?.id))
        assertThat("PRELIMINARY_SEMI_FINALS", CoreMatchers.`is`(match?.stage))
    }

    @Test
    fun testGetAllMatches(){
        enqueueResponse("matches.json")
        val response = LiveDataTestUtil.getValue(footballDataService.getAllMatches())
        val matchResponse = response.body
        assertThat(matchResponse, CoreMatchers.not(CoreMatchers.nullValue()))
        assertThat(matchResponse?.count, CoreMatchers.`is`(matchResponse?.matches?.size))
        val match = matchResponse?.matches?.get(0)
        assertThat(2013, CoreMatchers.`is`(match?.competition?.id))
        assertThat(204967, CoreMatchers.`is`(match?.id))
        assertThat("REGULAR_SEASON", CoreMatchers.`is`(match?.stage))
    }

    @Test
    fun testGetTeamMatches(){
        enqueueResponse("team_matches.json")
        val response = LiveDataTestUtil.getValue(footballDataService.getTeamMatches(200))
        val teamResponse = response.body
        assertThat(teamResponse, CoreMatchers.not(CoreMatchers.nullValue()))
        assertThat(teamResponse?.count, CoreMatchers.`is`(teamResponse?.matches?.size))
        val match = teamResponse?.matches?.get(0)
        assertThat(2000, CoreMatchers.`is`(match?.competition?.id))
        assertThat(200030, CoreMatchers.`is`(match?.id))
        assertThat("GROUP_STAGE", CoreMatchers.`is`(match?.stage))
    }

    @Test
    fun testGetTeamDetail(){
        enqueueResponse("team.json")
        val response = LiveDataTestUtil.getValue(footballDataService.getTeamDetail(200))
        val teamResponse = response.body
        assertThat(teamResponse, CoreMatchers.not(CoreMatchers.nullValue()))
        assertThat(18, CoreMatchers.`is`(teamResponse?.id))
        assertThat("Borussia Mönchengladbach", CoreMatchers.`is`(teamResponse?.name))
    }


}