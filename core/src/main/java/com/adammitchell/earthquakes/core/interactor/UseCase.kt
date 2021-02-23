
package com.adammitchell.earthquakes.core.interactor

import com.adammitchell.earthquakes.core.exception.Failure
import com.adammitchell.earthquakes.core.functional.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Failure, Type>

    operator fun invoke(params: Params, onResult: (Result<Failure, Type>) -> Unit = {}) {
        val job = GlobalScope.async(Dispatchers.IO) { run(params) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }
}
