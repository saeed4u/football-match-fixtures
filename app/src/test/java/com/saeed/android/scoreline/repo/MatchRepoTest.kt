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
    fun loadAllMatchesFromNetwork() {
        val allMatches = MutableLiveData(listOf(Match()))
        whenever(matchDao.getAllMatches()).thenReturn(allMatches)

        val response = MatchResponse(2, Competition(), listOf(Match(), Match()))
        val call = createSuccessCall(response)
        whenever(footballDataService.getAllMatches()).thenReturn(call)

        val data = matchRepo.getAllMatches()
        verify(matchDao).getAllMatches()
        verifyNoMoreInteractions(footballDataService)

        val observer = mock<Observer<Resource<List<Match>>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(footballDataService)
        val updatedData = MutableLiveData<List<Match>>()
        whenever(matchDao.getAllMatches()).thenReturn(updatedData)

        allMatches.postValue(null)

        verify(observer).onChanged(Resource.loading(null))
        verify(footballDataService).getAllMatches()
        verify(matchDao).insertAll(*response.matches.toTypedArray())

        updatedData.postValue(response.matches)
        verify(observer).onChanged(Resource.success(response.matches))
    }
}