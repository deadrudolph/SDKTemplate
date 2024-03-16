import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.deadrudolph.android.library")
    `maven-publish`
}

android {
    namespace = "com.deadrudolph.core"

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
                artifactId = "core"
                version = sdk.versions.sdk.get()
                from(components["release"])
            }
        }
    }
}

dependencies {
    debugImplementation(projects.navigation)
    releaseImplementation(sdk.navigation)

    implementation(libs.activityKtx)
    implementation(libs.appcompat)
    implementation(libs.composeUI)
    implementation(libs.material)
    implementation(libs.dagger)
    implementation(libs.daggerCompiler)
    implementation(libs.fragmentKtx)
    implementation(libs.timber)
}
