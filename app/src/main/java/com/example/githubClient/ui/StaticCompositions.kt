package com.example.githubClient.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import com.example.githubClient.Navigator

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("Navigator not found")
}

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("NavController not found")
}
