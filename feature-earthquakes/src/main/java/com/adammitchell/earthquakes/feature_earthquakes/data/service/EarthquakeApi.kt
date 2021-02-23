package com.adammitchell.earthquakes.feature_earthquakes.data.service

import com.adammitchell.earthquakes.feature_earthquakes.data.EarthquakesResponseEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthquakesApi {
    companion object {
        private const val PARAM_FORMATTED = "formatted"
        private const val PARAM_NORTH = "north"
        private const val PARAM_SOUTH = "south"
        private const val PARAM_EAST = "east"
        private const val PARAM_WEST = "west"
        private const val PARAM_USERNAME = "username"
        private const val EARTHQUAKES = "earthquakesJSON"
    }

    @GET(EARTHQUAKES) fun earthquakes(
        @Query(PARAM_FORMATTED) formatted: Boolean,
        @Query(PARAM_NORTH) north: Double,
        @Query(PARAM_SOUTH) south: Double,
        @Query(PARAM_EAST) east: Double,
        @Query(PARAM_WEST) west: Double,
        @Query(PARAM_USERNAME) username: String): Call<EarthquakesResponseEntity>
}