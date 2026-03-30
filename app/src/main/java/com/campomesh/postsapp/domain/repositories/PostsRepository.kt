package com.campomesh.postsapp.domain.repositories

import com.campomesh.postsapp.data.models.Post


interface PostsRepository {
    suspend fun getPosts(): List<Post>
}