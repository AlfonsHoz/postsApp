package com.campomesh.postsapp.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes(val route: String) {

    @Serializable
    data object HomeScreen : NavRoutes("/home")

    @Serializable
    data object Back : NavRoutes("pop")

    @Serializable
    data object PostDetailsScreen: NavRoutes("/post-detail/{postId}") {
        fun createRoute(postId: Int) = "/post-detail/$postId"
    }
}