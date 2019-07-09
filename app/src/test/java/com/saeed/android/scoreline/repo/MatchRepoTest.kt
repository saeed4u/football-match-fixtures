package com.saeed.android.scoreline.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.saeed.android.scoreline.api.service.FootballDataService
import com.saeed.android.scoreline.db.dao.MatchDao
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Match
import com.saeed.android.scoreline.model.MatchResponse
import com.saeed.android.scoreline.model.Resource
import com.saeed.android.scoreline.util.Util.createSuccessCall
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by Saeed on 2019-07-02.
 */
class MatchRepoTest {

    @get: Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var matchRepo: MatchRepo
    private val matchDao = mock<MatchDao>()
    private val footballDataService = mock<FootballDataService>()


    @Before
    fun init() {
        matchRepo = MatchRepo(footballDataService, matchDao)
    }

    @Test
    fun testGetAllMatches() {
        val allMatches = MutableLiveData<List<Match>>()
        whenever(matchDao.getAllMatches()).thenReturn(allMatches)

        val response = MatchResponse(2, Competition(), listOf(Match(), Match()))
        val call = createSuccessCall(response)
        whenever(footballDataService.getAllMatches()).thenReturn(call)

        val matches = matchRepo.getAllMatches()
        verify(matchDao).getAllMatches()
        verifyNoMoreInteractions(footballDataService)

        val matchObserver = mock<Observer<Resource<List<Match>>>>()
        matches.observeForever(matchObserver)
        verifyNoMoreInteractions(footballDataService)

        val updatedData = MutableLiveData<List<Match>>()
        whenever(matchDao.getAllMatches()).thenReturn(updatedData)

        allMatches.postValue(null)

        verify(matchObserver).onChanged(Resource.loading(null))
        verify(footballDataService).getAllMatches()
        verify(matchDao).insertAll(*response.matches.toTypedArray())
        updatedData.postValue(response.matches)
        verify(matchObserver).onChanged(Resource.success(response.matches))
    }

    @Test
    fun testGetTeamMatches(){
        val teamMatches = MutableLiveData<List<Match>>()
        whenever(matchDao.getTeamMatches(1L)).thenReturn(teamMatches)

        val response = MatchResponse(2, Competition(), listOf(Match(), Match()))
        val call = createSuccessCall(response)
        whenever(footballDataService.getTeamMatches(1L)).thenReturn(call)

        val matches = matchRepo.getTeamMatches(teamId = 1L)
        verify(matchDao).getTeamMatches(1L)
        verifyNoMoreInteractions(footballDataService)

        val matchObserver = mock<Observer<Resource<List<Match>>>>()
        matches.observeForever(matchObserver)
        verifyNoMoreInteractions(footballDataService)

        val updatedData = MutableLiveData<List<Match>>()
        whenever(matchDao.getTeamMatches(1L)).thenReturn(updatedData)

        teamMatches.postValue(null)

        verify(matchObserver).onChanged(Resource.loading(null))
        verify(footballDataService).getTeamMatches(1L)
        verify(matchDao).insertAll(*response.matches.toTypedArray())
        updatedData.postValue(response.matches)
        verify(matchObserver).onChanged(Resource.success(response.matches))
    }

    @Test
    fun testGetMatchesOfCompetition(){
        val compMatches = MutableLiveData<List<Match>>()
        whenever(matchDao.getCompetitionMatches(1L)).thenReturn(compMatches)

        val response = MatchResponse(2, Competition(), listOf(Match(), Match()))
        val call = createSuccessCall(response)
        whenever(footballDataService.getMatchesOfCompetition(1L)).thenReturn(call)

        val matches = matchRepo.getMatchesOfCompetition(competitionId = 1L)
        verify(matchDao).getCompetitionMatches(1L)
        verifyNoMoreInteractions(footballDataService)

        val matchObserver = mock<Observer<Resource<List<Match>>>>()
        matches.observeForever(matchObserver)
        verifyNoMoreInteractions(footballDataService)

        val updatedData = MutableLiveData<List<Match>>()
        whenever(matchDao.getCompetitionMatches(1L)).thenReturn(updatedData)

        compMatches.postValue(null)

        verify(matchObserver).onChanged(Resource.loading(null))
        verify(footballDataService).getMatchesOfCompetition(competitionId = 1L)
        verify(matchDao).insertAll(*response.matches.toTypedArray())
        updatedData.postValue(response.matches)
        verify(matchObserver).onChanged(Resource.success(response.matches))
    }
}