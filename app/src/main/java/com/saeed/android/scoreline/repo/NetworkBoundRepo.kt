package com.saeed.android.scoreline.repo

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.saeed.android.scoreline.api.ApiResponse
import com.saeed.android.scoreline.model.Resource
import timber.log.Timber


/**
 * Created by Saeed on 2019-06-21.
 */
abstract class NetworkBoundRepo<R, RT> {

    private val data: MediatorLiveData<Resource<R>> = MediatorLiveData()

    init {
        val dataFromDB = this.loadFromDatabase()
        data.addSource(dataFromDB) { result ->
            data.removeSource(dataFromDB)
            if (shouldFetchData(result)) {
                data.postValue(Resource.loading(null))
                fetchDataFromNetwork(dataFromDB)
            } else {
                data.addSource<R>(dataFromDB) {
                    setData(Resource.success(it))
                }
            }


        }
    }

    private fun fetchDataFromNetwork(dataFromDB: LiveData<R>) {
        Timber.d("Fetching data from network")
        val networkResponse = fetchDataFromApi()
        data.addSource(networkResponse) { response ->
            response?.let {
                when (it.isSuccessful) {
                    true -> {
                        response.body?.let { requestType ->
                            saveToDatabase(requestType)
                            val loadedData = loadFromDatabase()
                            data.addSource(loadedData) { newData ->
                                newData?.let {
                                    setData(Resource.success(newData))
                                }
                            }
                        }
                    }
                    false -> {
                        data.removeSource(dataFromDB)
                        onFetchFailed(response.message)
                        response.message?.let {
                            data.addSource<R>(dataFromDB) { newData ->
                                setData(Resource.error(it, newData))
                            }
                        }
                    }
                }
            }

        }
    }



    @MainThread
    private fun setData(newData: Resource<R>) {
        data.value = newData
    }

    fun getLiveData(): LiveData<Resource<R>> {
        return data
    }

    @WorkerThread
    protected abstract fun loadFromDatabase(): LiveData<R>

    @WorkerThread
    protected abstract fun saveToDatabase(data: RT)

    @MainThread
    protected abstract fun shouldFetchData(data: R?): Boolean

    @MainThread
    protected abstract fun fetchDataFromApi(): LiveData<ApiResponse<RT>>

    @MainThread
    protected abstract fun onFetchFailed(message: String?)


}