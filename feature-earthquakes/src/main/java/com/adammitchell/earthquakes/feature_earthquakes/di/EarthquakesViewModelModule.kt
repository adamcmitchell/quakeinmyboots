package com.adammitchell.earthquakes.feature_earthquakes.di

import androidx.lifecycle.ViewModel
import com.adammitchell.earthquakes.core.viewmodel.ViewModelKey
import com.adammitchell.earthquakes.feature_earthquakes.presentation.detail.EarthquakeDetailsViewModel
import com.adammitchell.earthquakes.core.viewmodel.ViewModelModule
import com.adammitchell.earthquakes.feature_earthquakes.presentation.list.EarthquakesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class EarthquakesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EarthquakesViewModel::class)
    abstract fun bindsEarthquakesViewModel(earthquakesViewModel: EarthquakesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EarthquakeDetailsViewModel::class)
    abstract fun bindsEarthquakeDetailsViewModel(earthquakeDetailsViewModel: EarthquakeDetailsViewModel): ViewModel
}