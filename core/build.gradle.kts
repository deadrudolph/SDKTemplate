plugins {
    id("com.deadrudolph.android.library")
}

android {
    namespace = "com.deadrudolph.core"
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
