package com.campomesh.postsapp.domain.repositories

import com.campomesh.postsapp.domain.models.Post


interface PostsRepository {
    suspend fun getPosts(): List<Post>

    suspend fun getPostByQuery(query: String): List<Post>

    suspend fun savePosts(posts: List<Post>): Boolean

    suspend fun getPost(id: Int): Post
}