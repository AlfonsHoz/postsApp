package com.campomesh.postsapp.presentation.ui.screens

import android.view.RoundedCorner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.campomesh.postsapp.presentation.ui.components.AppBar
import com.campomesh.postsapp.presentation.ui.components.Comment
import com.campomesh.postsapp.presentation.ui.components.LoadingIndicator
import com.campomesh.postsapp.presentation.viewModels.PostDetailViewModel
import org.intellij.lang.annotations.JdkConstants
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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
                title = "Post ${post?.id} Details"
            )
        }) { innerPadding ->
        if (loading)
            LoadingIndicator()
        else
            if (post == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(innerPadding),
                        text = "Post not found"
                    )
                }
            } else
                LazyColumn(
                    modifier = Modifier.padding(innerPadding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        Text(
                            text = post.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Text(
                                text = post.body,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                    item {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = comment,
                            placeholder = { Text("Comentar...") },
                            onValueChange = { postDetailViewModel.onCommentChange(it) },
                            minLines = 2,
                            maxLines = 5,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp)
                        )
                    }

                    item {
                        Button(
                            onClick = {
                                postDetailViewModel.sendComment()
                                focusManager.clearFocus()
                            },
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Enviar comentario")
                        }
                    }

                    item {
                        Text(
                            "Comentarios ${comments.size}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    items(comments.size) {
                        Comment(comment = comments[it])
                    }
                }
    }
}