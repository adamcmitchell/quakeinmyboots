
package com.adammitchell.earthquakes.feature_earthquakes.domain

import com.adammitchell.earthquakes.core.exception.Failure
import com.adammitchell.earthquakes.core.functional.Result
import com.adammitchell.earthquakes.feature_earthquakes.domain.usecase.GetEarthquakes

interface EarthquakesRepository {
    fun earthquakes(request: GetEarthquakes.Params): Result<Failure, List<Earthquake>>
}
