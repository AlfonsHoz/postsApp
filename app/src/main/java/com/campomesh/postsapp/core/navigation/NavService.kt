package com.campomesh.postsapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.campomesh.postsapp.core.extensions.isBack
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
            if (route.isBack())
                navController.popBackStack()
            else
                navController.navigate(route)
        }
    }

    NavHost(
        navController = navController,
        startDestination = NavRoutes.HomeScreen.route
    ) {
        composable(route = NavRoutes.HomeScreen.route) {
            HomeScreen()
        }

        composable(
            route = NavRoutes.PostDetailsScreen.route,
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: 0
            PostDetailsScreen(postId = postId)
        }
    }
}