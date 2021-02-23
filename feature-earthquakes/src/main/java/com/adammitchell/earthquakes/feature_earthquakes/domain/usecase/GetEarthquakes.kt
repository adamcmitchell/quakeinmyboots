package com.adammitchell.earthquakes.feature_earthquakes.domain.usecase

import com.adammitchell.earthquakes.core.interactor.UseCase
import com.adammitchell.earthquakes.feature_earthquakes.domain.Earthquake
import com.adammitchell.earthquakes.feature_earthquakes.domain.EarthquakesRepository
import javax.inject.Inject

class GetEarthquakes
@Inject constructor(private val earthquakesRepository: EarthquakesRepository) : UseCase<List<Earthquake>, GetEarthquakes.Params>() {

    override suspend fun run(params: Params) = earthquakesRepository.earthquakes(params)

    data class Params(val formatted: Boolean, val north: Double, val south: Double, val east: Double, val west: Double, val username: String) {
        companion object {
            fun default() =
                Params(
                    true,
                    44.1,
                    -9.9,
                    -22.4,
                    55.2,
                    "mkoppelman"
                )
        }
    }
}