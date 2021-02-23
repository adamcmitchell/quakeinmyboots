package com.adammitchell.earthquakes.feature_earthquakes.presentation.list

import android.content.Context
import android.content.Intent
import com.adammitchell.earthquakes.core.platform.BaseActivity
import com.adammitchell.earthquakes.core.platform.BaseFragment

class EarthquakesActivity: BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, EarthquakesActivity::class.java)
    }

    override fun fragment(): BaseFragment =
        EarthquakesFragment()
}