package com.rperez.flickersearchapp

import com.rperez.flickersearchapp.data.api.FlickrApiService
import com.rperez.flickersearchapp.data.model.FlickrResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiUnitTest {

    @Test
    fun testFlickrApiService() = runBlocking {
        val api = mockk<FlickrApiService>()
        val response = FlickrResponse(emptyList())
        coEvery { api.searchPhotos("cat") } returns response

        val result = api.searchPhotos("cat")
        assertEquals(response, result)
    }
}