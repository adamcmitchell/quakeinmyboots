package com.adammitchell.earthquakes.feature_earthquakes.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adammitchell.earthquakes.core.functional.Result
import com.adammitchell.earthquakes.core.platform.BaseViewModel
import com.adammitchell.earthquakes.feature_earthquakes.domain.Earthquake
import com.adammitchell.earthquakes.feature_earthquakes.domain.EarthquakeLocation
import com.adammitchell.earthquakes.feature_earthquakes.domain.usecase.GetEarthquakeLocation
import com.adammitchell.earthquakes.feature_earthquakes.domain.usecase.GetEarthquakes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class EarthquakesViewModel
@Inject constructor(private val getEarthquakes: GetEarthquakes,
                    private val getEarthquakeLocation: GetEarthquakeLocation) : BaseViewModel() {

    private val _earthquakes: MutableLiveData<List<EarthquakeView>> = MutableLiveData()
    val earthquakes: LiveData<List<EarthquakeView>> = _earthquakes

    fun loadEarthquakes() = getEarthquakes(GetEarthquakes.Params.default()) { it.fold(::handleFailure, ::handleEarthquakeList) }

    private fun handleEarthquakeList(earthquakes: List<Earthquake>) {
        GlobalScope.launch {
            val mapped = earthquakes.sortedByDescending { it.date }.map {
                when (val result = fetchLocationAsync(it).await()) {
                    is Result.Success -> earthquakeView(it, result.value);
                    else -> EarthquakeView(
                        it.id,
                        it.magnitude,
                        it.latitude,
                        it.longitude,
                        it.date,
                        it.depth,
                        null,
                        null
                    )
                }
            }
            GlobalScope.launch(Dispatchers.Main) {
                _earthquakes.value = mapped
            }
        }
    }

    private fun fetchLocationAsync(earthquake: Earthquake) = GlobalScope.async(Dispatchers.IO) {
        getEarthquakeLocation.run(GetEarthquakeLocation.Params(earthquake.latitude, earthquake.longitude))
    }

    private fun earthquakeView(earthquake: Earthquake, earthquakeLocation: EarthquakeLocation): EarthquakeView {
        return EarthquakeView(
            earthquake.id,
            earthquake.magnitude,
            earthquake.latitude,
            earthquake.longitude,
            earthquake.date,
            earthquake.depth,
            earthquakeLocation.countryCode,
            earthquakeLocation.locationName
        )
    }
}