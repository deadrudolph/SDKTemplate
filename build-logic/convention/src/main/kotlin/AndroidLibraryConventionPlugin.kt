import com.android.build.gradle.LibraryExtension
import com.deadrudolph.conventions.configureCommonAndroid
import com.deadrudolph.conventions.implementation
import com.deadrudolph.conventions.libsDependency
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                implementation(libsDependency("timber"))
                implementation(libsDependency("startup"))
            }

            extensions.configure<LibraryExtension> {
                configureCommonAndroid(this)
                defaultConfig.consumerProguardFiles("consumer-rules.pro")
            }
        }
    }
}
