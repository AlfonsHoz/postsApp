package com.campomesh.postsapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.campomesh.postsapp.presentation.ui.screens.HomeScreen
import com.campomesh.postsapp.presentation.ui.screens.PostDetailsScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NavService(
    navController: NavHostController = rememberNavController(),
    appNavigator: AppNavigator
) {
    LaunchedEffect(Unit) {
        appNavigator.navigationEvents.collectLatest { route ->
            navController.navigate(route)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen()
        }

        composable(
            route = Screens.PostDetailsScreen.route,
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: 0
            PostDetailsScreen(postId)
        }
    }
}