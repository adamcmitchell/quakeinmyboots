
package com.adammitchell.earthquakes.core.functional

import com.adammitchell.earthquakes.core.exception.Failure

sealed class Result<out L: Failure, out R> {

    data class Fail<out L: Failure>(val failure: L) : Result<L, Nothing>()
    data class Success<out R>(val value: R) : Result<Nothing, R>()

    val isSuccess get() = this is Success<R>
    val isFailure get() = this is Fail<L>

    fun <L: Failure> fail(failure: L) = Fail(failure)

    fun <R> success(value: R) = Success(value)

    fun fold(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Fail -> fnL(failure)
            is Success -> fnR(value)
        }
}