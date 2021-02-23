package com.adammitchell.earthquakes.feature_earthquakes.domain.usecase

import com.adammitchell.earthquakes.core.interactor.UseCase
import com.adammitchell.earthquakes.feature_earthquakes.domain.EarthquakeLocation
import com.adammitchell.earthquakes.feature_earthquakes.domain.GeocodeService
import javax.inject.Inject

class GetEarthquakeLocation
@Inject constructor(private val geocodeService: GeocodeService) : UseCase<EarthquakeLocation, GetEarthquakeLocation.Params>() {

    override suspend fun run(params: Params) = geocodeService.getLocation(params.latitude, params.longitude)

    data class Params(val latitude: Double, val longitude: Double);
}