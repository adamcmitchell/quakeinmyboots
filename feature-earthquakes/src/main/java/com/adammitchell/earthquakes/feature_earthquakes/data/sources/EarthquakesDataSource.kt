package com.adammitchell.earthquakes.feature_earthquakes.data.sources

import com.adammitchell.earthquakes.core.exception.Failure
import com.adammitchell.earthquakes.core.functional.Result
import com.adammitchell.earthquakes.core.platform.NetworkHandler
import com.adammitchell.earthquakes.feature_earthquakes.data.EarthquakesResponseEntity
import com.adammitchell.earthquakes.feature_earthquakes.data.service.EarthquakeService
import com.adammitchell.earthquakes.feature_earthquakes.domain.Earthquake
import com.adammitchell.earthquakes.feature_earthquakes.domain.usecase.GetEarthquakes
import retrofit2.Call
import javax.inject.Inject

interface EarthquakesDataSource {
    fun earthquakes(request: GetEarthquakes.Params): Result<Failure, List<Earthquake>>
}

class EarthquakesApiDataSource
@Inject constructor(private val networkHandler: NetworkHandler,
                    private val service: EarthquakeService
) : EarthquakesDataSource {

    override fun earthquakes(request: GetEarthquakes.Params): Result<Failure, List<Earthquake>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(service.earthquakes(request.formatted, request.north, request.south, request.east, request.west, request.username),
                { response -> response.earthquakes.map { entity -> entity.toEarthquake() } },
                EarthquakesResponseEntity.empty())
            false -> Result.Fail(Failure.NetworkConnection)
        }
    }

    private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Result<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Result.Success(transform((response.body() ?: default)))
                false -> Result.Fail(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Result.Fail(Failure.ServerError)
        }
    }
}