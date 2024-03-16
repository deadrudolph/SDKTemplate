import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.deadrudolph.android.library")
    id("com.deadrudolph.android.di")
    `maven-publish`
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
                artifactId = "common_database"
                version = sdk.versions.sdk.get()
                from(components["release"])
            }
        }
    }
}

android {
    namespace = "com.deadrudolph.common_database"

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}
