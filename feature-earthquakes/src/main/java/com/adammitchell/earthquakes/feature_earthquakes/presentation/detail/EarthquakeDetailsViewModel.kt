package com.adammitchell.earthquakes.feature_earthquakes.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adammitchell.earthquakes.core.platform.BaseViewModel
import com.adammitchell.earthquakes.feature_earthquakes.presentation.list.EarthquakeView
import javax.inject.Inject

class EarthquakeDetailsViewModel
@Inject constructor() : BaseViewModel() {

    private val _earthquakeDetails: MutableLiveData<EarthquakeDetailsView> = MutableLiveData()
    val earthquakeDetails: LiveData<EarthquakeDetailsView> = _earthquakeDetails

    fun loadEarthquakeDetails(earthquakeView: EarthquakeView) {
        _earthquakeDetails.value =
            EarthquakeDetailsView(
                id = earthquakeView.id,
                magnitude = earthquakeView.magnitude,
                latitude = earthquakeView.latitude,
                longitude = earthquakeView.longitude,
                date = earthquakeView.date,
                depth = earthquakeView.depth,
            )
    }
}