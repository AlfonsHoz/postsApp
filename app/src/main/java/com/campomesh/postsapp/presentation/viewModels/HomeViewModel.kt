package com.campomesh.postsapp.presentation.viewModels

import androidx.lifecycle.viewModelScope
import com.campomesh.postsapp.core.base.BaseViewModel
import com.campomesh.postsapp.core.events.ToastEvents
import com.campomesh.postsapp.core.navigation.AppNavigator
import com.campomesh.postsapp.core.navigation.NavRoutes
import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.useCases.GetPostsUseCase
import com.campomesh.postsapp.domain.useCases.SavePostsUseCase
import com.campomesh.postsapp.domain.useCases.SearchPostsUseCase
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
    private val searchPostsUseCase: SearchPostsUseCase,
    appNavigator: AppNavigator,
    toastEvents: ToastEvents
) : BaseViewModel(appNavigator, toastEvents) {

    private val _postsState = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _postsState.asStateFlow()

    private val _queryState = MutableStateFlow<String>("")
    val query = _queryState.asStateFlow()

    private val _postSavedState = MutableStateFlow<Boolean>(false)

    init {
        loadPosts()
    }

    private fun loadPosts() {
        loadingState.value = true
        viewModelScope.launch {
            _postsState.value = getPostsUseCase.invoke()
            if (_postsState.value.isNotEmpty())
                savePosts()
            else
                loadingState.value = false
        }
    }

    private fun savePosts() {
        viewModelScope.launch(context = Dispatchers.IO) {
            _postSavedState.value = savePostsUseCase.invoke(_postsState.value)
            if (!_postSavedState.value) {
                showToast("Error saving posts")
            }
            loadingState.value = false
        }
    }

    fun onPostClick(postIndex: Int) {
        appNavigator.navigateTo(NavRoutes.PostDetailsScreen.createRoute(posts.value[postIndex].id))
    }

    fun onQueryChange(queryValue: String) {
        _queryState.value = queryValue
        viewModelScope.launch(context = Dispatchers.IO) {
            _postsState.value = searchPostsUseCase.invoke(queryValue)
        }
    }
}