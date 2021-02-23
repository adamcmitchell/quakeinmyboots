
package com.adammitchell.earthquakes.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application
}
