package com.campomesh.postsapp.core.navigation

sealed class Screens(val route: String) {
    data object HomeScreen: Screens(route = "/home")
}