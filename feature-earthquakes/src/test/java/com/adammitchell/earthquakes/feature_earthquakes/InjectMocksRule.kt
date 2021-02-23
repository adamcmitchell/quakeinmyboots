
package com.adammitchell.earthquakes.feature_earthquakes

import io.mockk.MockKAnnotations
import org.junit.rules.TestRule

class InjectMocksRule {

    companion object {
        fun create(testClass: Any) = TestRule { statement, _ ->
            MockKAnnotations.init(testClass, relaxUnitFun = true)
            statement
        }
    }
}
