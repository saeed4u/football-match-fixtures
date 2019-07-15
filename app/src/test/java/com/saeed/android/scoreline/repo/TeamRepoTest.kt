package com.saeed.android.scoreline.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.saeed.android.scoreline.api.service.FootballDataService
import com.saeed.android.scoreline.db.dao.TeamDao
import com.saeed.android.scoreline.model.*
import com.saeed.android.scoreline.util.Util
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by Saeed on 2019-07-16.
 */
class TeamRepoTest {


    @get: Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var teamRepo: TeamRepo
    private val teamDao = mock<TeamDao>()
    private val footballDataService = mock<FootballDataService>()


    @Before
    fun init() {
        teamRepo = TeamRepo(footballDataService, teamDao)
    }

    @Test
    fun testGetAllMatches() {
        val allMatches = MutableLiveData<List<TeamAndPlayers>>()
        whenever(teamDao.getTeams()).thenReturn(allMatches)

        val response = TeamsResponse(2, Competition(), Season(), emptyList())
        val call = Util.createSuccessCall(response)
        whenever(footballDataService.getTeamsInCompetition(1L)).thenReturn(call)

        val matches = teamRepo.getTeamsInCompetition(1L)
        verify(teamDao).getTeams()
        verifyNoMoreInteractions(footballDataService)

        val matchObserver = mock<Observer<Resource<List<TeamAndPlayers>>>>()
        matches.observeForever(matchObserver)
        verifyNoMoreInteractions(footballDataService)

        val updatedData = MutableLiveData<List<TeamAndPlayers>>()
        whenever(teamDao.getTeams()).thenReturn(updatedData)

        allMatches.postValue(null)

        verify(matchObserver).onChanged(Resource.loading(null))
        verify(footballDataService).getTeamsInCompetition(1L)
        verify(teamDao).insertAll(*response.teams.toTypedArray())
        val teamAndPlayersList = arrayListOf<TeamAndPlayers>()
        response.teams.forEach {
            val teamAndPlayers = TeamAndPlayers()
            teamAndPlayers.team = it
            teamAndPlayers.players = emptyList()
            teamAndPlayersList.add(teamAndPlayers)
        }
        updatedData.postValue(teamAndPlayersList)
        verify(matchObserver).onChanged(Resource.success(teamAndPlayersList))
    }


}