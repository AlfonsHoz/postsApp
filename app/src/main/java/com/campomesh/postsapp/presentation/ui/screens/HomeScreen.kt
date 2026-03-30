package com.campomesh.postsapp.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.campomesh.postsapp.presentation.ui.components.AppBar
import com.campomesh.postsapp.presentation.ui.components.LoadingIndicator
import com.campomesh.postsapp.presentation.ui.components.Postcard
import com.campomesh.postsapp.presentation.ui.components.SearchPosts
import com.campomesh.postsapp.presentation.viewModels.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val posts = homeViewModel.posts.collectAsState().value
    val loading = homeViewModel.loading.collectAsState().value
    val query = homeViewModel.query.collectAsState().value

    val focusManager = LocalFocusManager.current
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    return Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            AppBar(title = "Posts")
        }) { innerPadding ->
        if (loading) {
            LoadingIndicator()
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            ) {
                item {
                    SearchPosts(
                        query = query,
                        onValueChange = {
                            homeViewModel.onQueryChange(it)
                        },
                        onClearClick = {
                            homeViewModel.onQueryChange("")
                            focusManager.clearFocus()
                            coroutineScope.launch {
                                listState.animateScrollToItem(0)
                            }
                        }
                    )
                }

                items(
                    posts.size, key = { posts[it].id }) { index ->
                    Box(modifier = Modifier.clickable() {
                        homeViewModel.onPostClick(index)
                        focusManager.clearFocus()
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