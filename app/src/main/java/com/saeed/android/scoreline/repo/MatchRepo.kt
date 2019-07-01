package com.saeed.android.scoreline.repo

import androidx.lifecycle.LiveData
import com.saeed.android.scoreline.api.ApiResponse
import com.saeed.android.scoreline.api.service.FootballDataService
import com.saeed.android.scoreline.db.dao.MatchDao
import com.saeed.android.scoreline.model.Match
import com.saeed.android.scoreline.model.MatchResponse
import com.saeed.android.scoreline.model.Resource
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Saeed on 2019-06-21.
 */
class MatchRepo @Inject constructor(
    private val footballDataService: FootballDataService,
    private val matchDao: MatchDao
) : Repo {

    init {
        Timber.d("MatchRepo initialized")
    }

    fun getAllMatches(refresh: Boolean, dateFrom: String = "", dateTo: String = ""): LiveData<Resource<List<Match>>> {
        return object : NetworkBoundRepo<List<Match>, MatchResponse>() {
            override fun loadFromDataSource(): LiveData<List<Match>> {
                return matchDao.getAllMatches()
            }

            override fun saveToDataSource(data: MatchResponse) {
                matchDao.insertAll(*data.matches.toTypedArray())
            }

            override fun shouldFetchData(data: List<Match>?): Boolean {
                return refresh || data == null || data.isEmpty()
            }

            override fun fetchDataFromApi(): LiveData<ApiResponse<MatchResponse>> {
                return footballDataService.getAllMatches(dateFrom, dateTo)
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("An error occurred: message $message")
            }

        }.getLiveData()
    }

}