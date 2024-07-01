/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/8.1.1/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    maven { setUrl("https://funczz.github.io/kotlin-fsm") }
    maven { setUrl("https://funczz.github.io/kotlin-sam") }
    maven { setUrl("https://funczz.github.io/kotlin-rocket-launcher-core") }
    maven { setUrl("https://funczz.github.io/kotlin-notifier") }
}

dependencies {
    implementation("com.github.funczz:rocket-launcher-core:0.2.0")
    implementation("com.github.funczz:notifier:0.4.0")
}

application {
    // Define the main class for the application.
    mainClass.set("com.github.funczz.kotlin.rocket_launcher.swing.MainClass")
}
