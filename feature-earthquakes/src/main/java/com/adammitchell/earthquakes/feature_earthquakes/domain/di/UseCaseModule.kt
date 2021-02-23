package com.adammitchell.earthquakes.feature_earthquakes.domain.di

import com.adammitchell.earthquakes.core.interactor.UseCase
import com.adammitchell.earthquakes.feature_earthquakes.domain.Earthquake
import com.adammitchell.earthquakes.feature_earthquakes.domain.EarthquakesRepository
import com.adammitchell.earthquakes.feature_earthquakes.domain.usecase.GetEarthquakes
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {
    @Provides
    fun provideEarthquakesUseCase(earthquakesRepository: EarthquakesRepository): UseCase<List<Earthquake>, GetEarthquakes.Params> =
        GetEarthquakes(
            earthquakesRepository
        )
}