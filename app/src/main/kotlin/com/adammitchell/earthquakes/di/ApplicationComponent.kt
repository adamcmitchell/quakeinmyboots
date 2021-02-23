
package com.adammitchell.earthquakes.di

import com.adammitchell.earthquakes.navigation.Navigator
import com.adammitchell.earthquakes.navigation.RouteActivity
import com.adammitchell.earthquakes.feature_earthquakes.di.EarthquakesComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, FeaturesModules::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(module: ApplicationModule): ApplicationComponent
    }

    fun earthquakeComponentFactory(): EarthquakesComponent.Factory

    fun inject(routeActivity: RouteActivity);
}

@Module(subcomponents = [EarthquakesComponent::class])
object FeaturesModules {

    @Provides
    fun providesNavigator(): Navigator {
        return Navigator()
    }
}
