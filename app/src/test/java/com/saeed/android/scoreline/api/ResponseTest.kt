package com.saeed.android.scoreline.api

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


/**
 * Created by Saeed on 2019-07-01.
 */
@RunWith(JUnit4::class)
class ResponseTest {

    @Test
    fun failure() {
        val apiResponse = ApiResponse<String>(Throwable("This is an error"))
        MatcherAssert.assertThat(apiResponse.isSuccessful, CoreMatchers.`is`(false))
        MatcherAssert.assertThat<String>(apiResponse.body, CoreMatchers.nullValue())
        MatcherAssert.assertThat(apiResponse.code, CoreMatchers.`is`(500))
        MatcherAssert.assertThat(apiResponse.message, CoreMatchers.`is`("This is an error"))
    }

    @Test
    fun success() {
        val apiResponse = ApiResponse(Response.success("Success"))
        MatcherAssert.assertThat(apiResponse.isSuccessful, CoreMatchers.`is`(true))
        MatcherAssert.assertThat<String>(apiResponse.body, CoreMatchers.`is`("Success"))
        MatcherAssert.assertThat(apiResponse.code, CoreMatchers.`is`(200))
        MatcherAssert.assertThat(apiResponse.message,CoreMatchers.`is`("OK"))
    }

}