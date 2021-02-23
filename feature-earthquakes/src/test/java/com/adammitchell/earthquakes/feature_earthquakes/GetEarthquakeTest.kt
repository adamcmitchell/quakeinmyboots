package com.adammitchell.earthquakes.feature_earthquakes

import com.adammitchell.earthquakes.core.functional.Result
import com.adammitchell.earthquakes.feature_earthquakes.domain.Earthquake
import com.adammitchell.earthquakes.feature_earthquakes.domain.EarthquakesRepository
import com.adammitchell.earthquakes.feature_earthquakes.domain.usecase.GetEarthquakes
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before

class GetEarthquakesTest: UnitTest() {

    private lateinit var getEarthquakes: GetEarthquakes

    @MockK
    private lateinit var earthquakesRepository: EarthquakesRepository

    @Before
    fun setUp() {
        getEarthquakes = GetEarthquakes(earthquakesRepository)
        every { earthquakesRepository.earthquakes(any()) } returns Result.Success(listOf(
            Earthquake.empty()
        ))
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getEarthquakes.run(GetEarthquakes.Params.default()) }

        verify(exactly = 1) { earthquakesRepository.earthquakes(any()) }
    }
}