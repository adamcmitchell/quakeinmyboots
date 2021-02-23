
package com.adammitchell.earthquakes.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adammitchell.earthquakes.core.R
import com.adammitchell.earthquakes.core.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
            savedInstanceState ?: supportFragmentManager.inTransaction { add(R.id.fragmentContainer, fragment()) }

    abstract fun fragment(): BaseFragment
}
