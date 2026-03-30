package com.campomesh.postsapp.presentation.viewModels

import androidx.lifecycle.viewModelScope
import com.campomesh.postsapp.core.base.BaseViewModel
import com.campomesh.postsapp.core.events.ToastEvents
import com.campomesh.postsapp.core.navigation.AppNavigator
import com.campomesh.postsapp.core.navigation.NavRoutes
import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.useCases.GetPostsUseCase
import com.campomesh.postsapp.domain.useCases.SavePostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val savePostsUseCase: SavePostsUseCase,
    appNavigator: AppNavigator,
    toastEvents: ToastEvents
) : BaseViewModel(appNavigator, toastEvents) {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _postSavedState = MutableStateFlow<Boolean>(false)

    init {
        loadPosts()
    }

    private fun loadPosts() {
        loadingState.value = true
        viewModelScope.launch {
            _posts.value = getPostsUseCase.invoke()
            if (_posts.value.isNotEmpty())
                savePosts()
            else
                loadingState.value = false
        }
    }

    private fun savePosts() {
        viewModelScope.launch(context = Dispatchers.IO) {
            _postSavedState.value = savePostsUseCase.invoke(_posts.value)
            if (!_postSavedState.value) {
                showToast("Error saving posts")
            }
            loadingState.value = false
        }
    }

    fun onPostClick(postIndex: Int) {
        appNavigator.navigateTo(NavRoutes.PostDetailsScreen.createRoute(posts.value[postIndex].id))
    }
}