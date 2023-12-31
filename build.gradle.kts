// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    //id("org.jetbrains.kotlin.android.extensions") version "2.0.0-Beta2"
}


buildscript {
    val kotlin_version = "1.3.61"
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.4")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        val nav_version = "2.7.6"
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")

    }
}
