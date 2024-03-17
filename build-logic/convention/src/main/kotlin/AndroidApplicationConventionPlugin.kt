import com.android.build.api.dsl.ApplicationExtension
import com.deadrudolph.conventions.AndroidConstants
import com.deadrudolph.conventions.configureCommonAndroid
import com.deadrudolph.conventions.implementation
import com.deadrudolph.conventions.libsDependency
import com.google.firebase.appdistribution.gradle.AppDistributionExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.firebase.appdistribution")
            }

            dependencies {
                implementation(libsDependency("startup"))
            }

            extensions.configure<ApplicationExtension> {
                configureCommonAndroid(this)
                defaultConfig.targetSdk = AndroidConstants.TARGET_SDK
                buildTypes {
                    getByName("debug") {
                        applicationIdSuffix = ".dev"
                    }
                    getByName("release") {
                        signingConfig = signingConfigs.getByName("release")
                    }
                }
            }

            extensions.configure<AppDistributionExtension> {
                serviceCredentialsFile = System.getenv("GOOGLE_APPLICATION_CREDENTIALS")
                groups = "QA"
                appId = "1:719859225345:android:d52478d38961df46a30100"
            }
        }
    }
}
