plugins {
  // Application Specific Plugins
  id(BuildPlugins.androidApplication)
  id(BuildPlugins.kotlinAndroid)
  id(BuildPlugins.kotlinKapt)
  id(BuildPlugins.kotlinAndroidExtensions)
}

android {
  compileSdkVersion(AndroidSdk.compile)

  defaultConfig {
    minSdkVersion(AndroidSdk.min)
    targetSdkVersion(AndroidSdk.target)

    applicationId = AndroidClient.appId
    versionCode = AndroidClient.versionCode
    versionName = AndroidClient.versionName
    testInstrumentationRunner = AndroidClient.testRunner
  }

  sourceSets {
    map { it.java.srcDir("src/${it.name}/kotlin") }

    //TODO: Remove this when migrating the DI framework
    getByName("main") { java.srcDir("$buildDir/generated/source/kapt/main") }
  }
}

dependencies {

  //Compile time dependencies
  kapt(Libraries.lifecycleCompiler)
  kapt(Libraries.daggerCompiler)
  compileOnly(Libraries.javaxAnnotation)
  compileOnly(Libraries.javaxInject)

  implementation(project(mapOf("path" to ":feature-earthquakes")))

}