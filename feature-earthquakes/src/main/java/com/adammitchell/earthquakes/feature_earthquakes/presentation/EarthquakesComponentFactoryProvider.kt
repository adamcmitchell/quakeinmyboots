package com.adammitchell.earthquakes.feature_earthquakes.presentation

import com.adammitchell.earthquakes.feature_earthquakes.di.EarthquakesComponent

interface EarthquakesComponentProvider {
    val earthquakeComponent: EarthquakesComponent;
}