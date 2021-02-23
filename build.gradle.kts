
buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath (BuildPlugins.androidGradlePlugin)
    classpath (BuildPlugins.kotlinGradlePlugin)
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}