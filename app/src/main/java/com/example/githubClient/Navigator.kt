package com.example.githubClient

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.githubClient.ui.detail.DetailScreen
import com.example.githubClient.ui.search.SearchScreen

class Navigator(private val navController: NavHostController) {
    fun navigateToDetail(repositoryId: String) {
        navController.navigate("${Screen.Detail.path}/$repositoryId")
    }
}

@Composable
fun RootNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Screen.Search.path,
    ) {
        composable(route = Screen.Search.path) {
            SearchScreen()
        }
        composable(
            route = "${Screen.Detail.path}/{repositoryId}",
            arguments = listOf(navArgument("repositoryId") { type = NavType.StringType })
        ) {
            DetailScreen()
        }
    }
}

sealed class Screen(
    val path: String
) {
    object Search : Screen("search")
    object Detail : Screen("detail")
}
