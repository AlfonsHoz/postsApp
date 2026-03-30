package com.campomesh.postsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.campomesh.postsapp.core.navigation.NavService
import com.campomesh.postsapp.core.navigation.Screens
import com.campomesh.postsapp.presentation.ui.theme.PostsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostsAppTheme {
                PostsApp()
            }
        }
    }
}

@Composable
fun PostsApp() {
    NavService(
        startDestination = Screens.HomeScreen.route
    )
}