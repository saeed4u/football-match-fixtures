package com.saeed.android.scoreline.api.service

import com.saeed.android.scoreline.api.ApiResponse
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Match
import com.saeed.android.scoreline.model.Team
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Saeed on 2019-06-20.
 */
interface FootballDataService {

    @GET("competitions")
    fun getAllCompetitions(): Single<ApiResponse<Competition>>

    @GET("competitions/{id}/teams")
    fun getTeamsInCompetition(@Path("id") competitionId: Long): Single<ApiResponse<Team>>

    @GET("competitions/{id}/matches")
    fun getMatchesOfCompetition(@Path("id") competitionId: Long, @Query("dateFrom") dateFrom: String = "", @Query("dateTo") dateTo: String = ""): Single<ApiResponse<Match>>

    @GET("matches")
    fun getAllMatches(@Query("dateFrom") dateFrom: String = "", @Query("dateTo") dateTo: String = ""): Single<ApiResponse<Match>>

    @GET("teams/{id}/matches")
    fun getTeamMatches(@Path("id") teamId: Long, @Query("dateFrom") dateFrom: String = "", @Query("dateTo") dateTo: String = ""): Single<ApiResponse<Match>>

}