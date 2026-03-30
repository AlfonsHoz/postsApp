package com.campomesh.postsapp.core.events

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToastEvents @Inject constructor() {
    private val _toastEvents = Channel<String>()
    val events = _toastEvents.receiveAsFlow()

    fun showToast(message: String) {
        _toastEvents.trySend(message)
    }
}