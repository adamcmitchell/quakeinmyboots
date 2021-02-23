plugins {
    // Application Specific Plugins
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)

        versionCode = AndroidClient.versionCode
        versionName = AndroidClient.versionName
        testInstrumentationRunner = AndroidClient.testRunner
    }
}

dependencies {

    //Compile time dependencies
    kapt(Libraries.lifecycleCompiler)
    kapt(Libraries.daggerCompiler)
    compileOnly(Libraries.javaxAnnotation)
    compileOnly(Libraries.javaxInject)

    // Application dependencies
    api(Libraries.kotlinStdLib)
    api(Libraries.kotlinCoroutines)
    api(Libraries.kotlinCoroutinesAndroid)
    api(Libraries.appCompat)
    api(Libraries.ktxCore)
    api(Libraries.constraintLayout)
    api(Libraries.viewModel)
    api(Libraries.liveData)
    api(Libraries.lifecycleExtensions)
    api(Libraries.cardView)
    api(Libraries.recyclerView)
    api(Libraries.material)
    api(Libraries.androidAnnotations)
    api(Libraries.dagger)
    api(Libraries.retrofit)
    api(Libraries.okHttpLoggingInterceptor)

    // Unit/Android tests dependencies
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.kluent)
    testImplementation(TestLibraries.robolectric)

    // Acceptance tests dependencies
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espressoCore)
    androidTestImplementation(TestLibraries.testExtJunit)
    androidTestImplementation(TestLibraries.testRules)
    androidTestImplementation(TestLibraries.espressoIntents)
}