package com.campomesh.postsapp.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens(val route: String) {

    @Serializable
    data object HomeScreen : Screens("/home")

    @Serializable
    data object PostDetailsScreen: Screens("/post-detail/{postId}") {
        fun createRoute(postId: Int) = "/post-detail/$postId"
    }
}