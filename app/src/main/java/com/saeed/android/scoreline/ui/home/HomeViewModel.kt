package com.saeed.android.scoreline.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Resource
import com.saeed.android.scoreline.repo.CompetitionRepo
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val competitionRepo: CompetitionRepo) :
    ViewModel() {


    private var competitionRefreshLiveData: MutableLiveData<Boolean> = MutableLiveData(true)
    private val competitionListLiveData: LiveData<Resource<List<Competition>>>

    init {
        Timber.d("initiating ${this.javaClass.simpleName}")

        competitionListLiveData = competitionRefreshLiveData.switchMap {
            competitionRepo.getCompetitions(it)
        }
    }

    fun getCompetitionsList() = competitionListLiveData.value
    fun postCompetition(refresh: Boolean = false) = competitionRefreshLiveData.postValue(refresh)
}