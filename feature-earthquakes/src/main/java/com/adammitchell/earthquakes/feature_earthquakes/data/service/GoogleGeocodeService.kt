package com.adammitchell.earthquakes.feature_earthquakes.data.service

import android.content.Context
import android.location.Geocoder
import com.adammitchell.earthquakes.core.exception.Failure
import com.adammitchell.earthquakes.feature_earthquakes.domain.EarthquakeLocation
import com.adammitchell.earthquakes.feature_earthquakes.domain.GeocodeService
import com.adammitchell.earthquakes.core.functional.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleGeocodeService
@Inject constructor(private val context: Context):
    GeocodeService {

    private val geocoder = Geocoder(context)

    override fun getLocation(latitude: Double, longitude: Double): Result<EarthquakeLocationFailure, EarthquakeLocation> {
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        if (addresses.size == 0) {
            return Result.Fail(EarthquakeLocationFailure.AddressNotFound)
        }
        val address = addresses[0]
        return Result.Success(EarthquakeLocation(address.countryCode, address.countryName ?: address.featureName))
    }
}

sealed class EarthquakeLocationFailure: Failure() {
    object AddressNotFound: EarthquakeLocationFailure()
}