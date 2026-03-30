package com.campomesh.postsapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.campomesh.postsapp.core.events.ToastEvents
import com.campomesh.postsapp.core.navigation.AppNavigator
import com.campomesh.postsapp.core.navigation.NavService
import com.campomesh.postsapp.presentation.ui.theme.PostsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appNavigator: AppNavigator

    @Inject
    lateinit var toastEvents: ToastEvents

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostsAppTheme {
                val context = LocalContext.current

                LaunchedEffect(Unit) {
                    toastEvents.events.collectLatest { message ->
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    }
                }

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