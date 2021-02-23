package com.adammitchell.earthquakes.feature_earthquakes.presentation.detail

import android.content.Context
import android.content.Intent
import com.adammitchell.earthquakes.core.platform.BaseActivity
import com.adammitchell.earthquakes.feature_earthquakes.presentation.list.EarthquakeView

class EarthquakeDetailsActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_EARTHQUAKE = "com.adammitchell.INTENT_PARAM_EARTHQUAKE"

        fun callingIntent(context: Context, movie: EarthquakeView): Intent {
            val intent = Intent(context, EarthquakeDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_EARTHQUAKE, movie)
            return intent
        }
    }

    override fun fragment() = EarthquakeDetailsFragment.forEarthquake(intent.getParcelableExtra(
        INTENT_EXTRA_PARAM_EARTHQUAKE
    ))
}