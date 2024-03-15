plugins {
    id("com.deadrudolph.android.library")
}

android {
    namespace = "com.deadrudolph.uicomponents"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {
    debugImplementation(projects.common.commonDomain)
    releaseImplementation(sdk.commonDomain)
    implementation(libs.composeUI)
    implementation(libs.composeMaterial)
    implementation(libs.composeUITooling)
    implementation(libs.composeFoundation)
}
