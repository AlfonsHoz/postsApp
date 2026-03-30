package com.campomesh.postsapp.presentation.viewModels

import androidx.lifecycle.viewModelScope
import com.campomesh.postsapp.core.base.BaseViewModel
import com.campomesh.postsapp.core.navigation.AppNavigator
import com.campomesh.postsapp.core.navigation.NavRoutes
import com.campomesh.postsapp.data.models.Post
import com.campomesh.postsapp.domain.useCases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    appNavigator: AppNavigator
) : BaseViewModel(appNavigator) {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()


    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _posts.value = getPostsUseCase.invoke()
        }
    }

    fun onPostClick(postIndex: Int) {
        appNavigator.navigateTo(NavRoutes.PostDetailsScreen.createRoute(posts.value[postIndex].id))
    }
}