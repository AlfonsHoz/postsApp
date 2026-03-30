package com.campomesh.postsapp.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.campomesh.postsapp.presentation.viewModels.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val posts = homeViewModel.posts.collectAsState().value
    val loading = homeViewModel.loading.collectAsState().value

    return Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (loading) {
            CircularProgressIndicator()
        } else {
            Column(modifier = Modifier.padding(innerPadding)) {
                posts.map {
                    Text(text = it.title)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostsAppPreview() {
    HomeScreen()
}