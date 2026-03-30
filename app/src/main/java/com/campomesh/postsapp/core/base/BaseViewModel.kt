package com.campomesh.postsapp.core.base

import androidx.lifecycle.ViewModel
import com.campomesh.postsapp.core.events.ToastEvents
import com.campomesh.postsapp.core.navigation.AppNavigator
import com.campomesh.postsapp.core.navigation.NavRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel(
    protected val appNavigator: AppNavigator,
    protected val toastEvents: ToastEvents
) : ViewModel() {
    protected val loadingState: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val loading = loadingState.asStateFlow()

    fun goBack() {
        appNavigator.navigateTo(NavRoutes.Back)
    }

    protected fun showToast(message: String) {
        toastEvents.showToast(message)
    }

}