package com.campomesh.postsapp.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.campomesh.postsapp.presentation.ui.components.AppBar
import com.campomesh.postsapp.presentation.ui.components.LoadingIndicator
import com.campomesh.postsapp.presentation.viewModels.PostDetailViewModel

@Composable
fun PostDetailsScreen(
    postDetailViewModel: PostDetailViewModel = hiltViewModel()
) {
    //viewmodel states
    val loading = postDetailViewModel.loading.collectAsState().value
    val post = postDetailViewModel.post.collectAsState().value
    val comment = postDetailViewModel.comment.collectAsState().value
    val comments = postDetailViewModel.comments.collectAsState().value

    val focusManager = LocalFocusManager.current

    return Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            AppBar(
                showBack = true,
                onBack = { postDetailViewModel.goBack() },
                title = "Post Details"
            )
        }) { innerPadding ->
        if (loading)
            LoadingIndicator()
        else
            if (post == null)
                Text(text = "Post not found")
            else
                Column(modifier = Modifier.padding(innerPadding)) {
                    Text(text = post.title)
                    Text(text = post.body)
                    OutlinedTextField(
                        value = comment,
                        onValueChange = { postDetailViewModel.onCommentChange(it) },
                        minLines = 2,
                        maxLines = 5
                    )
                    Button(
                        onClick = {
                            postDetailViewModel.sendComment()
                            focusManager.clearFocus()
                        },
                        content = { Text("Enviar comentario") })
                    LazyColumn(
                        contentPadding = PaddingValues(10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(count = comments.size) { commentsIndex ->
                            Text(text = comments[commentsIndex])
                        }
                    }
                }
    }
}