plugins {
    id("com.deadrudolph.android.library")
    id("com.deadrudolph.android.di")
}

android {
    namespace = "com.deadrudolph.feature_home"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
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
