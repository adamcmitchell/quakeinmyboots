package com.adammitchell.earthquakes.feature_earthquakes.presentation.detail

import android.os.Parcel
import com.adammitchell.earthquakes.core.platform.KParcelable
import com.adammitchell.earthquakes.core.platform.parcelableCreator
import com.adammitchell.earthquakes.core.platform.readDate
import com.adammitchell.earthquakes.core.platform.writeDate
import com.adammitchell.earthquakes.feature_earthquakes.presentation.list.EarthquakeView
import java.util.*

data class EarthquakeDetailsView(val id: String,
                          val magnitude: Double,
                          val latitude: Double,
                          val longitude: Double,
                          val date: Date,
                          val depth: Double) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::EarthquakeView)
    }

    constructor(parcel: Parcel) : this(parcel.readString()!!, parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDate()!!, parcel.readDouble())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(id)
            writeDouble(magnitude)
            writeDouble(latitude)
            writeDouble(longitude)
            writeDate(date)
            writeDouble(depth)
        }
    }
}