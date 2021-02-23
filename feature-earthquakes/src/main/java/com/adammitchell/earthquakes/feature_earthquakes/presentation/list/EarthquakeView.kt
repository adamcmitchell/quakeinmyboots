package com.adammitchell.earthquakes.feature_earthquakes.presentation.list

import android.os.Parcel
import com.adammitchell.earthquakes.core.platform.KParcelable
import com.adammitchell.earthquakes.core.platform.parcelableCreator
import com.adammitchell.earthquakes.core.platform.readDate
import com.adammitchell.earthquakes.core.platform.writeDate
import java.util.*

data class EarthquakeView(val id: String,
                          val magnitude: Double,
                          val latitude: Double,
                          val longitude: Double,
                          val date: Date,
                          val depth: Double,
                          val countryCode: String?,
                          val locationName: String?) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::EarthquakeView)
    }

    constructor(parcel: Parcel) : this(parcel.readString()!!, parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDate()!!, parcel.readDouble(), parcel.readString(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(id)
            writeDouble(magnitude)
            writeDouble(latitude)
            writeDouble(longitude)
            writeDate(date)
            writeDouble(depth)
            writeString(countryCode)
            writeString(locationName)
        }
    }
}