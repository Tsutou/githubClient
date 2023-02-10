package com.example.githubClient.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.githubClient.Navigator
import com.example.githubClient.RootNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val navigator = remember { Navigator(navController) }

                Scaffold { paddingValues ->
                    CompositionLocalProvider(
                        LocalNavController provides navController,
                        LocalNavigator provides navigator,
                    ) {
                        RootNavigation(Modifier.padding(paddingValues), navController)
                    }
                }
            }
        }
    }
}
