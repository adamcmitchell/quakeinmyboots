package com.adammitchell.earthquakes.feature_earthquakes.domain

import java.util.*

data class Earthquake(val id: String,
                      val magnitude: Double,
                      val latitude: Double,
                      val longitude: Double,
                      val date: Date,
                      val depth: Double,
                      val source: String) {
    companion object {
        fun empty(): Earthquake {
            return Earthquake("", 0.0, 0.0, 0.0, Date(), 0.0, "");
        }
    }
}

