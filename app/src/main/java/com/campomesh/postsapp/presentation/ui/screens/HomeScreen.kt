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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.campomesh.postsapp.presentation.ui.components.AppBar
import com.campomesh.postsapp.presentation.ui.components.LoadingIndicator
import com.campomesh.postsapp.presentation.ui.components.Postcard
import com.campomesh.postsapp.presentation.viewModels.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val posts = homeViewModel.posts.collectAsState().value
    val loading = homeViewModel.loading.collectAsState().value
    val query = homeViewModel.query.collectAsState().value

    val focusManager = LocalFocusManager.current

    return Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            AppBar(title = "Posts")
        }) { innerPadding ->
        if (loading) {
            LoadingIndicator()
        } else {
            Column(modifier = Modifier.padding(innerPadding)) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { homeViewModel.onQueryChange(it) },
                    label = { Text("Search") },
                    placeholder = { Text("Buscar posts...") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Buscar"
                        )
                    },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    homeViewModel.onQueryChange("")
                                    focusManager.clearFocus()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = "Limpiar"
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                )
                LazyColumn(
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
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
}

@Preview(showBackground = true)
@Composable
fun PostsAppPreview() {
    HomeScreen()
}