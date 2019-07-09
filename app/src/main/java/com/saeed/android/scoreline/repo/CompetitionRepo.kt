package com.saeed.android.scoreline.repo

import androidx.lifecycle.LiveData
import com.saeed.android.scoreline.api.ApiResponse
import com.saeed.android.scoreline.api.service.FootballDataService
import com.saeed.android.scoreline.db.dao.CompetitionDao
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.CompetitionResponse
import com.saeed.android.scoreline.model.Resource
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Saeed on 2019-07-09.
 */
class CompetitionRepo @Inject constructor(private val competitionDao: CompetitionDao, private val footballDataService: FootballDataService) : Repo {

    fun getCompetitions(refresh: Boolean = false): LiveData<Resource<List<Competition>>> {
        return object : NetworkBoundRepo<List<Competition>,CompetitionResponse>(){
            override fun loadFromDatabase(): LiveData<List<Competition>> {
                return competitionDao.getCompetitions()
            }

            override fun saveToDatabase(data: CompetitionResponse) {
                competitionDao.insertAll(*data.competitions.toTypedArray())
            }

            override fun shouldFetchData(data: List<Competition>?): Boolean {
                return refresh || data == null || data.isEmpty()
            }

            override fun fetchDataFromApi(): LiveData<ApiResponse<CompetitionResponse>> {
                return footballDataService.getAllCompetitions()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("Fetching competitions failed")
            }

        }.getLiveData()
    }

}