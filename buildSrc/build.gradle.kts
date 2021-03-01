object Dependencies {
    const val AndroidBuildTools = "com.android.tools.build:gradle:4.1.0"
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation(Dependencies.AndroidBuildTools)
}