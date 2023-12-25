pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        maven ( url =("https://android-sdk.is.com/"))
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven ( url =("https://android-sdk.is.com/"))

    }
}

rootProject.name = "Valley Soft"
include(":app")
