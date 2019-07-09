package com.saeed.android.scoreline.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.saeed.android.scoreline.api.service.FootballDataService
import com.saeed.android.scoreline.db.dao.CompetitionDao
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.CompetitionResponse
import com.saeed.android.scoreline.model.Resource
import com.saeed.android.scoreline.util.Util
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by Saeed on 2019-07-09.
 */
class CompetitionRepoTest {

    @get: Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var competitionRepo: CompetitionRepo
    private val competitionDao = mock<CompetitionDao>()
    private val footballDataService = mock<FootballDataService>()


    @Before
    fun init() {
        competitionRepo = CompetitionRepo(competitionDao, footballDataService)
    }

    @Test
    fun testGetAllMatches() {
        val allCompetitions = MutableLiveData<List<Competition>>()
        whenever(competitionDao.getCompetitions()).thenReturn(allCompetitions)

        val response = CompetitionResponse(2, listOf(Competition()))
        val call = Util.createSuccessCall(response)
        whenever(footballDataService.getAllCompetitions()).thenReturn(call)

        val competitions = competitionRepo.getCompetitions()
        verify(competitionDao).getCompetitions()
        verifyNoMoreInteractions(footballDataService)

        val competitionObserver = mock<Observer<Resource<List<Competition>>>>()
        competitions.observeForever(competitionObserver)
        verifyNoMoreInteractions(footballDataService)

        val updatedData = MutableLiveData<List<Competition>>()
        whenever(competitionDao.getCompetitions()).thenReturn(updatedData)

        allCompetitions.postValue(null)

        verify(competitionObserver).onChanged(Resource.loading(null))
        verify(footballDataService).getAllCompetitions()
        verify(competitionDao).insertAll(*response.competitions.toTypedArray())
        updatedData.postValue(response.competitions)
        verify(competitionObserver).onChanged(Resource.success(response.competitions))
    }

}