package com.saeed.android.scoreline.repo

import androidx.lifecycle.LiveData
import com.saeed.android.scoreline.api.ApiResponse
import com.saeed.android.scoreline.api.service.FootballDataService
import com.saeed.android.scoreline.db.dao.TeamDao
import com.saeed.android.scoreline.model.Resource
import com.saeed.android.scoreline.model.TeamAndPlayers
import com.saeed.android.scoreline.model.TeamsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Saeed on 2019-07-15.
 */
class TeamRepo @Inject constructor(
    private val footballDataService: FootballDataService,
    private val teamDao: TeamDao
) : Repo {

    fun getTeamsInCompetition(
        competitionId: Long,
        refresh: Boolean = false
    ): LiveData<Resource<List<TeamAndPlayers>>> {
        val scope = CoroutineScope(Dispatchers.IO)
        return object : NetworkBoundRepo<List<TeamAndPlayers>, TeamsResponse>() {
            override fun loadFromDatabase(): LiveData<List<TeamAndPlayers>> {
                return teamDao.getTeams()
            }

            override fun saveToDatabase(data: TeamsResponse) {
                scope.launch {
                    teamDao.insertAll(*data.teams.toTypedArray())
                }
            }

            override fun shouldFetchData(data: List<TeamAndPlayers>?): Boolean {
                return refresh || data == null || data.isEmpty()
            }

            override fun fetchDataFromApi(): LiveData<ApiResponse<TeamsResponse>> {
                return footballDataService.getTeamsInCompetition(competitionId)
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("An error occurred $message")
            }

        }.getLiveData()
    }

}