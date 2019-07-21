package com.saeed.android.scoreline.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.saeed.android.scoreline.model.Competition
import com.saeed.android.scoreline.model.Resource
import com.saeed.android.scoreline.repo.CompetitionRepo
import com.saeed.android.scoreline.util.NullLiveData
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val competitionRepo: CompetitionRepo) :
    ViewModel() {


    private var competitionRefreshLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val competitionListLiveData: LiveData<Resource<List<Competition>>>

    init {
        competitionListLiveData = competitionRefreshLiveData.switchMap {
            competitionRefreshLiveData.value?.let {
                competitionRepo.getCompetitions(it)
            } ?: NullLiveData.create()
        }
    }

    fun refreshCompetitions(refresh: Boolean = false) =
        competitionRefreshLiveData.postValue(refresh)
}