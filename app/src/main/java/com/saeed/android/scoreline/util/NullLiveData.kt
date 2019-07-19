package com.saeed.android.scoreline.util

import androidx.lifecycle.LiveData


/**
 * Created by Saeed on 2019-07-19.
 */
class NullLiveData<T>: LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create() = NullLiveData<T>()
    }

}