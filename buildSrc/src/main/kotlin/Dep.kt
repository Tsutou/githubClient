object Versions {
    const val compose = "1.3.0"
}

object Dep {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.3.1"
    const val googleServices = "com.google.gms:google-services:4.3.14"
    const val material = "com.google.android.material:material:1.7.0"
    const val timber = "com.jakewharton.timber:timber:5.0.1"
    const val desugarJdk = "com.android.tools:desugar_jdk_libs:1.1.5"
    const val junit = "junit:junit:4.+"
    const val mockk = "io.mockk:mockk:1.12.3"
    const val truth = "com.google.truth:truth:1.1.3"
    const val robolectric = "org.robolectric:robolectric:4.7.3"

    object Kotlin {
        private const val version = "1.7.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.6.4"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object Dagger {
        private const val version = "2.44.2"
        const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.9.0"
        const val appcompat = "androidx.appcompat:appcompat:1.6.0-alpha01"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:2.5.1"
        const val dataStorePreferences = "androidx.datastore:datastore-preferences:1.0.0"
        const val activityCompose = "androidx.activity:activity-compose:1.6.1"
        const val navigationCompose = "androidx.navigation:navigation-compose:2.5.3"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val materialIconsCore =
                "androidx.compose.material:material-icons-core:${Versions.compose}"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:${Versions.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        }
    }

    object Accompanist {
        private const val version = "0.28.0"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:$version"
        const val navigationAnimation =
            "com.google.accompanist:accompanist-navigation-animation:$version"
        const val pager = "com.google.accompanist:accompanist-pager:$version"
    }

    object Apollo {
        private const val version = "3.2.2"
        const val gradlePlugin = "com.apollographql.apollo3:apollo-gradle-plugin:$version"
        const val runtime = "com.apollographql.apollo3:apollo-runtime:$version"
        const val cache = "com.apollographql.apollo3:apollo-normalized-cache:$version"
    }

    object OkHttp {
        private const val okhttp_version = "4.9.3"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okhttp_version"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
    }

    object Coil {
        private const val version = "2.0.0-rc03"
        const val compose = "io.coil-kt:coil-compose:$version"
        const val gif = "io.coil-kt:coil-gif:$version"
    }
}
