package com.adammitchell.earthquakes.feature_earthquakes.data

import com.google.gson.annotations.SerializedName

data class EarthquakesResponseEntity(@SerializedName("earthquakes") val earthquakes: List<EarthquakeEntity>) {
    companion object {
        fun empty(): EarthquakesResponseEntity =
            EarthquakesResponseEntity(emptyList())
    }

}