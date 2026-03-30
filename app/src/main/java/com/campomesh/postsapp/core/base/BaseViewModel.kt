package com.campomesh.postsapp.core.base

import androidx.lifecycle.ViewModel
import com.campomesh.postsapp.core.navigation.AppNavigator
import com.campomesh.postsapp.core.navigation.NavRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel(
    protected val appNavigator: AppNavigator
) : ViewModel() {

    fun goBack() {
        appNavigator.navigateTo(NavRoutes.Back)
    }

    protected val loadingState: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val loading = loadingState.asStateFlow()
}