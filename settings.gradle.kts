enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "template_mvvm_sdk"

include(
    "sampleapp",
    "core",
    "navigation",
    "uicomponents",
    "common:common-network",
    "common:common-database",
    "common:common-di",
    "common:common-domain",
)
include(
    "features:home:feature-home-domain",
    "features:home:feature-home"
)
include(
    "features:profile:feature-profile",
    "features:profile:feature-profile-domain"
)

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://maven.pkg.github.com/deadrudolph/SDKTemplate") {
            credentials {
                username = "deadrudolph"
                // Readonly token for Github Packages
                password = "<Contact the code owner to get a ReadOnly token>"
            }
        }
    }
}

dependencyResolutionManagement {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://jitpack.io")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://maven.pkg.github.com/deadrudolph/SDKTemplate") {
            credentials {
                username = "deadrudolph"
                // Readonly token for Github Packages
                password = "<Contact the code owner to get a ReadOnly token>"
            }
        }
    }

    versionCatalogs {
        create("sdk") {
            from(files("gradle/sdk.versions.toml"))
        }
    }
}
