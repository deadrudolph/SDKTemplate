import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.deadrudolph.android.library")
    id("com.deadrudolph.android.di")
    `maven-publish`
}

android {
    namespace = "com.deadrudolph.common_network"

    buildFeatures {
        buildConfig = true
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

afterEvaluate {
    val githubProperties = loadProperties("github.properties")
    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/deadrudolph/SDKTemplate")
                credentials {
                    username = githubProperties.getProperty("gpr.usr")
                    password = githubProperties.getProperty("gpr.key")
                }
            }
        }
        publications {
            create<MavenPublication>("release") {
                groupId = "com.deadrudolph"
                artifactId = "common_network"
                version = sdk.versions.sdk.get()
                from(components["release"])
            }
        }
    }
}

dependencies {
    debugImplementation(projects.common.commonDi)
    debugImplementation(projects.common.commonDomain)

    releaseImplementation(sdk.commonDi)
    releaseImplementation(sdk.commonDomain)

    implementation(libs.dagger)
    ksp(libs.daggerCompiler)
    implementation(libs.httpLoggingInterceptor)
    implementation(libs.kotlinStdlib)
    implementation(libs.moshiKotlin)
    implementation(libs.moshiAdapters)
    implementation(libs.retrofit)
    implementation(libs.retrofitMoshiConverter)

    debugImplementation(libs.chuck)

    releaseImplementation(libs.chuckNoOp)
}
