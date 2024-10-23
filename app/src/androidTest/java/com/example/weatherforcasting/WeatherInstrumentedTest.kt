package com.example.weatherforcasting

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherforcasting.data.model.City
import com.example.weatherforcasting.data.source.AppDataBase
import com.example.weatherforcasting.data.source.local.dao.CityDao
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class WeatherInstrumentedTest {
    private lateinit var database: AppDataBase
    private lateinit var cityDao: CityDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()
        cityDao = database.cityDao()
    }

    @Test
    fun testDataBaseCity() {
        runBlocking {
            val city = City("test_cairo", "test_cairo", 1, 1.0, 1.0)
            cityDao.insertCity(city)
            val fetchedCity = cityDao.getCityById(1)
            assertNotNull(fetchedCity)
            assertEquals(fetchedCity?.cityNameEn, "test_cairo")

        }
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.weatherforcasting", appContext.packageName)
    }
    @After
    fun afterTest() {
        database.close()

    }
}