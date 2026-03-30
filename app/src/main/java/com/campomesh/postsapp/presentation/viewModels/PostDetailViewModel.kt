package com.campomesh.postsapp.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.campomesh.postsapp.core.base.BaseViewModel
import com.campomesh.postsapp.core.events.ToastEvents
import com.campomesh.postsapp.core.navigation.AppNavigator
import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.useCases.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostsUseCase: GetPostUseCase,
    savedStateHandle: SavedStateHandle,
    appNavigator: AppNavigator,
    toastEvents: ToastEvents
) : BaseViewModel(appNavigator, toastEvents) {

    private val postId: Int = savedStateHandle.get<Int>("postId") ?: 0

    private val _postState = MutableStateFlow<Post?>(null)
    val post = _postState.asStateFlow()

    private val _commentState = MutableStateFlow<String>("")
    val comment = _commentState.asStateFlow()

    private val _commentsState = MutableStateFlow<List<String>>(listOf())
    val comments = _commentsState.asStateFlow()

    init {
        getPost()
    }

    private fun getPost() {
        loadingState.value = true
        viewModelScope.launch(context = Dispatchers.IO) {
            _postState.value = getPostsUseCase.invoke(postId)
            if (_postState.value == null) {
                showToast("Error getting post")
            }
            loadingState.value = false
        }
    }

    fun sendComment() {
        loadingState.value = true
        viewModelScope.launch(context = Dispatchers.IO) {
            if (comment.value.isNotEmpty()) {
                _commentsState.value = _commentsState.value + comment.value
                showToast("Comment sent")
                _commentState.value = ""
            } else {
                showToast("Comment cannot be empty")
            }
            loadingState.value = false
        }
    }

    fun onCommentChange(value: String) {
        _commentState.value = value
    }
}