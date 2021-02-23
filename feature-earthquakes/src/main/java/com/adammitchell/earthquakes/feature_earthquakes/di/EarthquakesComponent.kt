package com.adammitchell.earthquakes.feature_earthquakes.di

import com.adammitchell.earthquakes.feature_earthquakes.presentation.detail.EarthquakeDetailsFragment
import com.adammitchell.earthquakes.feature_earthquakes.presentation.list.EarthquakesFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent(modules = [EarthquakesDataSourceModule::class, EarthquakesViewModelModule::class, EarthquakesRepositoryModule::class])
interface EarthquakesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EarthquakesComponent
    }

    fun inject(earthquakesFragment: EarthquakesFragment)
    fun inject(earthquakeDetailsFragment: EarthquakeDetailsFragment)
}