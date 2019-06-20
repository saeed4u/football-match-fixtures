package com.saeed.android.scoreline.api.service

import androidx.lifecycle.LiveData
import com.saeed.android.scoreline.api.ApiResponse
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Match
import com.saeed.android.scoreline.model.Team
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Saeed on 2019-06-20.
 */
interface FootballDataService {

    @GET("competitions")
    fun getAllCompetitions(): LiveData<ApiResponse<List<Competition>>>

    @GET("competitions/{id}/teams")
    fun getTeamsInCompetition(@Path("id") competitionId: Long): LiveData<ApiResponse<List<Team>>>

    @GET("competitions/{id}/matches")
    fun getMatchesOfCompetition(@Path("id") competitionId: Long, @Query("dateFrom") dateFrom: String = "", @Query("dateTo") dateTo: String = ""): LiveData<ApiResponse<List<Match>>>

    @GET("matches")
    fun getAllMatches(@Query("dateFrom") dateFrom: String = "", @Query("dateTo") dateTo: String = ""): LiveData<ApiResponse<List<Match>>>

    @GET("teams/{id}/matches")
    fun getTeamMatches(@Path("id") teamId: Long, @Query("dateFrom") dateFrom: String = "", @Query("dateTo") dateTo: String = ""): LiveData<ApiResponse<List<Match>>>

}