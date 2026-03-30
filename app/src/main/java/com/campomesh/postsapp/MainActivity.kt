package com.campomesh.postsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.campomesh.postsapp.core.navigation.AppNavigator
import com.campomesh.postsapp.core.navigation.NavService
import com.campomesh.postsapp.presentation.ui.theme.PostsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostsAppTheme {
                PostsApp(appNavigator)
            }
        }
    }
}

@Composable
fun PostsApp(appNavigator: AppNavigator) {
    NavService(
        appNavigator = appNavigator
    )
}