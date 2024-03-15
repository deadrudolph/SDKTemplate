plugins {
    id("com.deadrudolph.android.library")
    id("com.deadrudolph.android.di")
}

android {
    namespace = "com.deadrudolph.navigation"
}

dependencies {
    debugImplementation(projects.common.commonDi)
    releaseImplementation(sdk.commonDi)

    implementation(libs.dagger)
    ksp(libs.daggerCompiler)
    implementation(libs.fragmentKtx)
    implementation(libs.navigation)
}
