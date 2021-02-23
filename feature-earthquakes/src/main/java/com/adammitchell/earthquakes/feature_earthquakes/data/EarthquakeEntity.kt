package com.adammitchell.earthquakes.feature_earthquakes.data

import com.adammitchell.earthquakes.feature_earthquakes.domain.Earthquake
import com.google.gson.annotations.SerializedName
import java.util.*

data class EarthquakeEntity(@SerializedName("eqid") val id: String,
                            @SerializedName("magnitude") val magnitude: Double,
                            @SerializedName("lat") val latitude: Double,
                            @SerializedName("lng") val longitude: Double,
                            @SerializedName("datetime") val date: Date,
                            @SerializedName("depth") val depth: Double,
                            @SerializedName("src") val source: String) {
    fun toEarthquake() = Earthquake(
        id = this.id,
        magnitude = this.magnitude,
        latitude = this.latitude,
        longitude = this.longitude,
        date = this.date,
        depth = this.depth,
        source = this.source
    )
    companion object {
        fun empty(): EarthquakeEntity {
            return EarthquakeEntity("", 0.0, 0.0, 0.0, Date(), 0.0, "");
        }
    }
}