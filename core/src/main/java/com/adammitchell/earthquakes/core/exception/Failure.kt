
package com.adammitchell.earthquakes.core.exception

open class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
}
