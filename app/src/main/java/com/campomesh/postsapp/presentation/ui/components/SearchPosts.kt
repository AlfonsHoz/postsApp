package com.campomesh.postsapp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchPosts(
    query: String,
    onValueChange: (String) -> Unit,
    onClearClick: () -> Unit
) = OutlinedTextField(
    value = query,
    onValueChange = onValueChange,
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
                onClick = onClearClick
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