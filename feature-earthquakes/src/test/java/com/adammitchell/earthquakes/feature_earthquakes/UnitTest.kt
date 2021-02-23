
package com.adammitchell.earthquakes.feature_earthquakes

import org.junit.Rule

abstract class UnitTest {

    @Suppress("LeakingThis")
    @Rule @JvmField val injectMocks = InjectMocksRule.create(this@UnitTest)
}
