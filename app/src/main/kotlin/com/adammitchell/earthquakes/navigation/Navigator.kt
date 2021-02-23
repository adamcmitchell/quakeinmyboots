
package com.adammitchell.earthquakes.navigation

import android.content.Context
import com.adammitchell.earthquakes.feature_earthquakes.presentation.list.EarthquakesActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor() {

    fun showMain(context: Context) {
        showEarthquakes(context)
    }

    private fun showEarthquakes(context: Context) = context.startActivity(EarthquakesActivity.callingIntent(context))
}


