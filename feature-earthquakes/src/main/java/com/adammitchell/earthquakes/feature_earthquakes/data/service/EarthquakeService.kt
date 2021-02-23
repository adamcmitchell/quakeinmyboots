package com.adammitchell.earthquakes.feature_earthquakes.data.service

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EarthquakeService
@Inject constructor(retrofit: Retrofit) : EarthquakesApi {
    private val earthquakesApi by lazy { retrofit.create(EarthquakesApi::class.java) }
    override fun earthquakes(
        formatted: Boolean,
        north: Double,
        south: Double,
        east: Double,
        west: Double,
        username: String
    ) = earthquakesApi.earthquakes(formatted = formatted, north = north, south = south, east = east, west =  west, username = username);


}