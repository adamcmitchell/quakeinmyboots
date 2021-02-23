package com.adammitchell.earthquakes.feature_earthquakes.domain

import com.adammitchell.earthquakes.core.functional.Result
import com.adammitchell.earthquakes.feature_earthquakes.data.service.EarthquakeLocationFailure

interface GeocodeService {
    fun getLocation(latitude: Double, longitude: Double): Result<EarthquakeLocationFailure, EarthquakeLocation>
}