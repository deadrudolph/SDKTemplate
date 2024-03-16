import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.deadrudolph.android.library")
    id("com.deadrudolph.android.di")
    `maven-publish`
}

android {
    namespace = "com.deadrudolph.feature_home"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
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
                artifactId = "feature_home"
                version = sdk.versions.sdk.get()
                from(components["release"])
            }
        }
    }
}

dependencies {
    debugImplementation(projects.common.commonDi)
    debugImplementation(projects.common.commonDomain)
    debugImplementation(projects.core)
    debugImplementation(projects.navigation)
    debugImplementation(projects.uicomponents)
    debugImplementation(projects.features.home.featureHomeDomain)

    releaseImplementation(sdk.commonDi)
    releaseImplementation(sdk.commonDomain)
    releaseImplementation(sdk.core)
    releaseImplementation(sdk.navigation)
    releaseImplementation(sdk.uiComponents)
    releaseImplementation(sdk.featureHomeDomain)

    implementation(libs.appcompat)
    implementation(libs.composeUI)
    implementation(libs.composeMaterial)
    implementation(libs.composeUITooling)
    implementation(libs.composeFoundation)
    implementation(libs.dagger)
    ksp(libs.daggerCompiler)

    implementation(libs.fragmentKtx)
    implementation(libs.material)
    implementation(libs.viewModelScope)

    implementation(libs.navigation)
}
