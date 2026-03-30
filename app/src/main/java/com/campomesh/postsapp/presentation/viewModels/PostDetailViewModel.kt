package com.campomesh.postsapp.presentation.viewModels

import com.campomesh.postsapp.core.base.BaseViewModel
import com.campomesh.postsapp.core.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    appNavigator: AppNavigator
) : BaseViewModel(appNavigator) {
    
}