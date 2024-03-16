plugins {
    id("com.deadrudolph.android.application")
    id("com.deadrudolph.android.test")
    id("com.deadrudolph.android.di")
}

android {

    namespace = "com.deadrudolph.template_mvvm_sdk"

    defaultConfig {
        applicationId = "com.deadrudolph.template_mvvm_sdk"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {
    debugImplementation(projects.common.commonDi)
    debugImplementation(sdk.core)
    debugImplementation(projects.navigation)
    debugImplementation(projects.uicomponents)
    debugImplementation(projects.features.home.featureHome)
    debugImplementation(projects.features.profile.featureProfile)

    releaseImplementation(sdk.commonDi)
    releaseImplementation(sdk.core)
    releaseImplementation(sdk.navigation)
    releaseImplementation(sdk.uiComponents)
    releaseImplementation(sdk.featureHome)
    releaseImplementation(sdk.featureProfile)

    implementation(libs.appcompat)
    implementation(libs.coreKtx)
    implementation(libs.material)
    implementation(libs.timber)
    implementation(libs.composeActivity)
    implementation(libs.composeUI)
    implementation(libs.composeMaterial)
    implementation(libs.composeUITooling)
    implementation(libs.composeFoundation)

    implementation(libs.navigation)
}
