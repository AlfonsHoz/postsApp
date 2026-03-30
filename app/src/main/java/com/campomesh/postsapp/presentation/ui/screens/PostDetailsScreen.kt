package com.campomesh.postsapp.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.campomesh.postsapp.presentation.ui.components.AppBar
import com.campomesh.postsapp.presentation.ui.components.LoadingIndicator
import com.campomesh.postsapp.presentation.viewModels.PostDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailsScreen(
    postDetailViewModel: PostDetailViewModel = hiltViewModel(),
    postId: Int
) {

    val loading = postDetailViewModel.loading.collectAsState().value

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
            Column(modifier = Modifier.padding(innerPadding)) {
                Text(text = "Post Title")
                Text(text = "Post Body")
                LazyColumn {
                    items(count = 3) {
                        Text("Comment $it")
                    }
                }
            }
    }
}