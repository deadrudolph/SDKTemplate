package com.deadrudolph.conventions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.Properties

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureCommonAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = AndroidConstants.COMPILE_SDK

        defaultConfig {
            minSdk = AndroidConstants.MIN_SDK
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            getByName("debug") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        signingConfigs {
            signingConfigs {
                getByName("debug") {
                    storeFile = file("$rootDir/sampleapp/debug.keystore")
                }

                create("release") {
                    val debugKeystoreFile = rootProject.file("mock_credentials.properties")
                    val releaseKeystoreFile = rootProject.file("release_keystore.properties")

                    val propsFile = if (releaseKeystoreFile.exists()) {
                        releaseKeystoreFile
                    } else {
                        debugKeystoreFile
                    }

                    val props = Properties()
                    props.load(FileInputStream(propsFile))

                    storeFile = file(props.getProperty("storeFile"))
                    storePassword = props.getProperty("storePassword")
                    keyAlias = props.getProperty("keyAlias")
                    keyPassword = props.getProperty("keyPassword")
                }
            }
        }
    }

    configureKotlin()
}

/**
 * Configure base Kotlin options
 */
private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
}
