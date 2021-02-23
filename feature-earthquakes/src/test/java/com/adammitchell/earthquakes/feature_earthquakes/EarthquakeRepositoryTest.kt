package com.adammitchell.earthquakes.feature_earthquakes

import com.adammitchell.earthquakes.core.exception.Failure
import com.adammitchell.earthquakes.core.functional.Result
import com.adammitchell.earthquakes.core.platform.NetworkHandler
import com.adammitchell.earthquakes.feature_earthquakes.data.EarthquakeEntity
import com.adammitchell.earthquakes.feature_earthquakes.data.EarthquakesResponseEntity
import com.adammitchell.earthquakes.feature_earthquakes.data.repository.EarthquakeRepositoryImpl
import com.adammitchell.earthquakes.feature_earthquakes.data.service.EarthquakeService
import com.adammitchell.earthquakes.feature_earthquakes.data.sources.EarthquakesApiDataSource
import com.adammitchell.earthquakes.feature_earthquakes.domain.Earthquake
import com.adammitchell.earthquakes.feature_earthquakes.domain.usecase.GetEarthquakes
import io.mockk.Called
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.util.*

class EarthquakeRepositoryTest : UnitTest() {

    private lateinit var repository: EarthquakeRepositoryImpl

    @MockK
    private lateinit var networkHandler: NetworkHandler
    @MockK
    private lateinit var service: EarthquakeService

    @MockK
    private lateinit var earthquakesCall: Call<EarthquakesResponseEntity>
    @MockK
    private lateinit var earthquakesResponse: Response<EarthquakesResponseEntity>

    @Before
    fun setUp() {
        repository = EarthquakeRepositoryImpl(EarthquakesApiDataSource(networkHandler, service))
    }

    @Test
    fun `should return empty list by default`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { earthquakesResponse.body() } returns null
        every { earthquakesResponse.isSuccessful } returns true
        every { earthquakesCall.execute() } returns earthquakesResponse
        every { service.earthquakes(any(), any(), any(), any(), any(), any()) } returns earthquakesCall

        val movies = repository.earthquakes(GetEarthquakes.Params.default())

        movies shouldEqual Result.Success(emptyList<Earthquake>())
        verify(exactly = 1) { service.earthquakes(any(), any(), any(), any(), any(), any()) }
    }

    @Test fun `should get earthquake list from service`() {

        val currentDate = Date();

        every { networkHandler.isNetworkAvailable() } returns true
        every { earthquakesResponse.body() } returns EarthquakesResponseEntity(listOf(EarthquakeEntity("test", 0.0, 0.0, 0.0, currentDate, 0.0, "moon")))
        every { earthquakesResponse.isSuccessful } returns true
        every { earthquakesCall.execute() } returns earthquakesResponse
        every { service.earthquakes(any(), any(), any(), any(), any(), any()) } returns earthquakesCall

        val movies = repository.earthquakes(GetEarthquakes.Params.default())

        movies shouldEqual Result.Success(listOf(Earthquake("test", 0.0, 0.0, 0.0, currentDate, 0.0, "moon")))
        verify(exactly = 1) { service.earthquakes(any(), any(), any(), any(), any(), any()) }
    }
//
    @Test fun `movies service should return network failure when no connection`() {
        every { networkHandler.isNetworkAvailable() } returns false

        val movies = repository.earthquakes(GetEarthquakes.Params.default())

        movies shouldBeInstanceOf Result::class.java
        movies.isFailure shouldEqual true
        movies.fold({ failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java }, {})
        verify { service wasNot Called }
    }
//
    @Test fun `movies service should return server error if no successful response`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { earthquakesResponse.isSuccessful } returns false
        every { earthquakesCall.execute() } returns earthquakesResponse
        every { service.earthquakes(any(), any(), any(), any(), any(), any()) } returns earthquakesCall

        val movies = repository.earthquakes(GetEarthquakes.Params.default())

        movies shouldBeInstanceOf Result::class.java
        movies.isFailure shouldEqual true
        movies.fold({ failure -> failure shouldBeInstanceOf Failure.ServerError::class.java }, {})
    }

    @Test fun `movies request should catch exceptions`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { earthquakesCall.execute() } returns earthquakesResponse
        every { service.earthquakes(any(), any(), any(), any(), any(), any()) } returns earthquakesCall

        val movies = repository.earthquakes(GetEarthquakes.Params.default())

        movies shouldBeInstanceOf Result::class.java
        movies.isFailure shouldEqual true
        movies.fold({ failure -> failure shouldBeInstanceOf Failure.ServerError::class.java }, {})
    }
}