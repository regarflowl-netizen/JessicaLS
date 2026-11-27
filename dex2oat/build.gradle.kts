plugins { alias(libs.plugins.agp.lib) }

android {
    namespace = "org.jessicals.dex2oat"

    androidResources { enable = false }

    externalNativeBuild { cmake { path("src/main/cpp/CMakeLists.txt") } }
}
