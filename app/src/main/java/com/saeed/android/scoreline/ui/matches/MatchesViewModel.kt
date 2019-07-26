package com.saeed.android.scoreline.ui.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.saeed.android.scoreline.model.Match
import com.saeed.android.scoreline.model.Resource
import com.saeed.android.scoreline.repo.MatchRepo
import com.saeed.android.scoreline.util.NullLiveData
import javax.inject.Inject


/**
 * Created by Saeed on 2019-07-26.
 */
class MatchesViewModel @Inject constructor(private val matchRepo: MatchRepo) : ViewModel(){

    private var matchesRefreshLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val matchesListLiveData: LiveData<Resource<List<Match>>>

    init {
        matchesListLiveData = matchesRefreshLiveData.switchMap {
            matchesRefreshLiveData.value?.let {
                matchRepo.getAllMatches(it)
            } ?: NullLiveData.create()
        }
    }

    fun refreshMatches(refresh: Boolean = false) =
        matchesRefreshLiveData.postValue(refresh)

}