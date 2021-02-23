
package com.adammitchell.earthquakes

import android.app.Application
import com.adammitchell.earthquakes.di.ApplicationComponent
import com.adammitchell.earthquakes.di.ApplicationModule
import com.adammitchell.earthquakes.di.DaggerApplicationComponent
import com.adammitchell.earthquakes.feature_earthquakes.di.EarthquakesComponent
import com.adammitchell.earthquakes.feature_earthquakes.presentation.EarthquakesComponentProvider

class AndroidApplication : Application(),
    EarthquakesComponentProvider {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent.factory().create(ApplicationModule(this))
    }

    override val earthquakeComponent: EarthquakesComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        appComponent.earthquakeComponentFactory().create()
    }
}
