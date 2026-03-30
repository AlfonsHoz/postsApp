package com.campomesh.postsapp.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.campomesh.postsapp.core.base.BaseViewModel
import com.campomesh.postsapp.core.events.ToastEvents
import com.campomesh.postsapp.core.navigation.AppNavigator
import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.useCases.GetCommentsUseCase
import com.campomesh.postsapp.domain.useCases.GetPostUseCase
import com.campomesh.postsapp.domain.useCases.SaveCommentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostsUseCase: GetPostUseCase,
    private val saveCommentUseCase: SaveCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
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
            getComments()
        }
    }

    fun sendComment() {
        loadingState.value = true
        viewModelScope.launch(context = Dispatchers.IO) {
            if (comment.value.isNotEmpty()) {
                val saved = saveCommentUseCase.invoke(comment = comment.value, postId = postId)
                if (saved){
                    _commentState.value = ""
                    getComments()
                }
            } else {
                showToast("Comment cannot be empty")
            }
            loadingState.value = false
        }
    }

    private fun getComments() {
        viewModelScope.launch(context = Dispatchers.IO) {
            _commentsState.value = getCommentsUseCase.invoke(postId).map { it.comment }
            loadingState.value = false
        }
    }

    fun onCommentChange(value: String) {
        _commentState.value = value
    }
}