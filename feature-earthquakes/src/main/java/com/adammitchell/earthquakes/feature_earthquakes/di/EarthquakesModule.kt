package com.adammitchell.earthquakes.feature_earthquakes.di

import android.content.Context
import com.adammitchell.earthquakes.feature_earthquakes.domain.EarthquakesRepository
import com.adammitchell.earthquakes.core.network.NetworkModule
import com.adammitchell.earthquakes.core.platform.NetworkHandler
import com.adammitchell.earthquakes.feature_earthquakes.data.repository.EarthquakeRepositoryImpl
import com.adammitchell.earthquakes.feature_earthquakes.data.service.EarthquakeService
import com.adammitchell.earthquakes.feature_earthquakes.data.service.GoogleGeocodeService
import com.adammitchell.earthquakes.feature_earthquakes.data.sources.EarthquakesApiDataSource
import com.adammitchell.earthquakes.feature_earthquakes.data.sources.EarthquakesDataSource
import com.adammitchell.earthquakes.feature_earthquakes.domain.GeocodeService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule::class])
class EarthquakesDataSourceModule {

    @Provides
    fun providesEarthquakesDataSource(networkHandler: NetworkHandler, service: EarthquakeService): EarthquakesDataSource = EarthquakesApiDataSource(networkHandler, service)

    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.geonames.org/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create()
    }

    @Provides
    fun provideEarthquakeService(retrofit: Retrofit): EarthquakeService = EarthquakeService(retrofit)

    @Provides
    fun providesGeocodeService(context: Context): GeocodeService = GoogleGeocodeService(context)
}

@Module
class EarthquakesRepositoryModule {
    @Provides
    fun providesEarthquakesRepository(dataSource: EarthquakesDataSource): EarthquakesRepository = EarthquakeRepositoryImpl(dataSource = dataSource);
}