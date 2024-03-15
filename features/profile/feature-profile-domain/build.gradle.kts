plugins {
    id("com.deadrudolph.android.library")
    id("com.deadrudolph.android.di")
}

android {
    namespace = "com.deadrudolph.feature_profile_domain"
}

dependencies {
    debugImplementation(projects.common.commonDi)
    debugImplementation(projects.common.commonNetwork)
    debugImplementation(projects.common.commonDomain)

    releaseImplementation(sdk.commonDi)
    releaseImplementation(sdk.commonNetwork)
    releaseImplementation(sdk.commonDomain)

    implementation(libs.dagger)
    ksp(libs.daggerCompiler)
    implementation(libs.retrofit)
    implementation(libs.moshiKotlin)
}
