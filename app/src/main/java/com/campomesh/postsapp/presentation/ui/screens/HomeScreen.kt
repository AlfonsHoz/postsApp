package com.campomesh.postsapp.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.campomesh.postsapp.presentation.ui.components.Postcard
import com.campomesh.postsapp.presentation.viewModels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val posts = homeViewModel.posts.collectAsState().value
    val loading = homeViewModel.loading.collectAsState().value

    return Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(title = { Text("Posts") })
        }) { innerPadding ->
        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(
                    posts.size, key = { posts[it].id }) { index ->
                    Box(modifier = Modifier.clickable() {
                        homeViewModel.onPostClick(index)
                    }) {
                        Postcard(post = posts[index])
                    }
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