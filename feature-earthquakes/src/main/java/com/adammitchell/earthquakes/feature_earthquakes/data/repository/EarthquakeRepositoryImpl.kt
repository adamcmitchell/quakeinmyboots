package com.adammitchell.earthquakes.feature_earthquakes.data.repository

import com.adammitchell.earthquakes.feature_earthquakes.domain.EarthquakesRepository
import com.adammitchell.earthquakes.core.exception.Failure
import com.adammitchell.earthquakes.core.functional.Result
import com.adammitchell.earthquakes.feature_earthquakes.data.sources.EarthquakesDataSource
import com.adammitchell.earthquakes.feature_earthquakes.domain.Earthquake
import com.adammitchell.earthquakes.feature_earthquakes.domain.usecase.GetEarthquakes

class EarthquakeRepositoryImpl(val dataSource: EarthquakesDataSource): EarthquakesRepository {
    override fun earthquakes(request: GetEarthquakes.Params): Result<Failure, List<Earthquake>> {
        return dataSource.earthquakes(request);
    }
}