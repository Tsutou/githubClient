import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.apollographql.apollo3")
}

android {
    namespace = "com.example.githubClient"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.githubClient"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val githubOauthKey = (rootProject.ext["localProperties"] as Properties)["GITHUB_PAT"]
        buildConfigField("String", "GITHUB_OAUTH_KEY", "\"$githubOauthKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

apollo {
    packageName.set("com.example.githubClient")

    generateOptionalOperationVariables.set(false)

    introspection {
        endpointUrl.set("https://api.github.com/graphql")
        schemaFile.set(file("src/main/graphql/schema.graphqls"))
        val githubOauthKey = (rootProject.ext["localProperties"] as Properties)["GITHUB_PAT"]
        headers.put("Authorization", "Bearer $githubOauthKey")
    }
}

dependencies {
    coreLibraryDesugaring(Dep.desugarJdk)

    implementation(Dep.material)

    implementation(Dep.Kotlin.Coroutines.android)

    implementation(Dep.AndroidX.core)
    implementation(Dep.AndroidX.appcompat)
    implementation(Dep.AndroidX.lifecycleRuntime)
    implementation(Dep.AndroidX.dataStorePreferences)
    implementation(Dep.AndroidX.lifecycleProcess)
    implementation(Dep.AndroidX.activityCompose)
    implementation(Dep.AndroidX.viewModelCompose)
    implementation(Dep.AndroidX.navigationCompose)
    implementation(Dep.AndroidX.hiltNavigationCompose)

    implementation(Dep.AndroidX.Compose.ui)
    implementation(Dep.AndroidX.Compose.material)
    implementation(Dep.AndroidX.Compose.materialIconsCore)
    implementation(Dep.AndroidX.Compose.materialIconsExtended)
    implementation(Dep.AndroidX.Compose.uiTooling)

    implementation(Dep.Accompanist.insets)
    implementation(Dep.Accompanist.systemUiController)
    implementation(Dep.Accompanist.navigationAnimation)
    implementation(Dep.Accompanist.pager)

    implementation(Dep.Dagger.hiltAndroid)
    kapt(Dep.Dagger.hiltCompiler)

    implementation(Dep.Apollo.runtime)
    implementation(Dep.Apollo.cache)

    implementation(Dep.OkHttp.okHttp)
    implementation(Dep.OkHttp.logging)

    implementation(Dep.Coil.compose)
    implementation(Dep.Coil.gif)

    implementation(Dep.timber)

    testImplementation(Dep.junit)
    testImplementation(Dep.mockk)
    testImplementation(Dep.truth)
    testImplementation(Dep.robolectric)
    testImplementation(Dep.Kotlin.Coroutines.test)
}
