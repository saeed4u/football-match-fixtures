package com.saeed.android.scoreline

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTestUtil {

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        liveData.observeForever(object : Observer<T> {
            override fun onChanged(type: T?) {
                data[0] = type
                latch.countDown()
                liveData.removeObserver(this)
            }
        })

        latch.await(3, TimeUnit.SECONDS)
        return data[0] as T
    }
}
