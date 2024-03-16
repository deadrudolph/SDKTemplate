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
        maven(url = "https://maven.pkg.github.com/deadrudolph/template_mvvm_sdk") {
            credentials {
                username = "deadrudolph"
                // Readonly token for Github Packages
                password = "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"
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
        maven(url = "https://maven.pkg.github.com/deadrudolph/template_mvvm_sdk") {
            credentials {
                username = "deadrudolph"
                // Readonly token for Github Packages
                password = "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"
            }
        }
    }

    versionCatalogs {
        create("sdk") {
            from(files("gradle/sdk.versions.toml"))
        }
    }
}
