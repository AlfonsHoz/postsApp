package com.campomesh.postsapp.core.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigator @Inject constructor() {
    private val _navigationEvents = Channel<String>()
    val navigationEvents = _navigationEvents.receiveAsFlow()

    fun navigateTo(screen: Screens) {
        _navigationEvents.trySend(screen.route)
    }

    fun navigateTo(route: String) {
        _navigationEvents.trySend(route)
    }
}