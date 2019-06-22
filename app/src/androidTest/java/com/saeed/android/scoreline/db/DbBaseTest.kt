package com.saeed.android.scoreline.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule


/**
 * Created by Saeed on 2019-06-21.
 */
abstract class DbBaseTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    lateinit var db: AppDatabase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java)
            .build()
        init()
    }

    @After
    fun closeDb() {
        db.close()
    }

    abstract fun init()

}