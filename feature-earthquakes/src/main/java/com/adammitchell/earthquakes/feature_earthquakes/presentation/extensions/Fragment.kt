package com.adammitchell.earthquakes.feature_earthquakes.presentation.extensions

import com.adammitchell.earthquakes.core.platform.BaseFragment
import com.adammitchell.earthquakes.feature_earthquakes.di.EarthquakesComponent
import com.adammitchell.earthquakes.feature_earthquakes.presentation.EarthquakesComponentProvider

fun BaseFragment.getEarthquakeComponent(): EarthquakesComponent = (activity?.application as EarthquakesComponentProvider).earthquakeComponent