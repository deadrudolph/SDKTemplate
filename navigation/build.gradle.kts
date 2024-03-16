import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.deadrudolph.android.library")
    id("com.deadrudolph.android.di")
    `maven-publish`
}

android {
    namespace = "com.deadrudolph.navigation"

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
                url = uri("https://maven.pkg.github.com/deadrudolph/template_mvvm_sdk")
                credentials {
                    username = githubProperties.getProperty("gpr.usr")
                    password = githubProperties.getProperty("gpr.key")
                }
            }
        }
        publications {
            create<MavenPublication>("release") {
                groupId = "com.deadrudolph"
                artifactId = "navigation"
                version = sdk.versions.sdk.get()
                from(components["release"])
            }
        }
    }
}

dependencies {
    debugImplementation(projects.common.commonDi)
    releaseImplementation(sdk.commonDi)

    implementation(libs.dagger)
    ksp(libs.daggerCompiler)
    implementation(libs.fragmentKtx)
    implementation(libs.navigation)
}
