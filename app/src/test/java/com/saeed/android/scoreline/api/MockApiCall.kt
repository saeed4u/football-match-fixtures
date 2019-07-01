package com.saeed.android.scoreline.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.saeed.android.scoreline.api.adapter.LiveDataCallAdapterFactory
import com.saeed.android.scoreline.api.service.FootballDataService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException


/**
 * Created by Saeed on 2019-07-01.
 * T is the Retrofit service
 */
@RunWith(JUnit4::class)
abstract class MockApiCall{

    protected lateinit var footballDataService: FootballDataService

    @get: Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer

    @Before
    @Throws(IOException::class)
    fun init(){
        mockWebServer = MockWebServer()
        mockWebServer.start()

        footballDataService = createService()
    }

    @After
    @Throws(IOException::class)
    fun shutDown(){
        mockWebServer.shutdown()
    }

    private fun createService(): FootballDataService{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(FootballDataService::class.java)
    }

    fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()){
        javaClass.classLoader?.getResource("response/$fileName")?.let {
            val mockedResponse = MockResponse()
            for ((key, value) in headers) {
                mockedResponse.addHeader(key, value)
            }
            mockedResponse.setBody(it.readText())
            mockWebServer.enqueue(mockedResponse)
        }
    }
}