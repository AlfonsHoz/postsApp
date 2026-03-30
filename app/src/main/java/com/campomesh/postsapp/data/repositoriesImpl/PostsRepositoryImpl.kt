package com.campomesh.postsapp.data.repositoriesImpl

import com.campomesh.postsapp.data.datasources.PostsApiService
import com.campomesh.postsapp.data.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import javax.inject.Inject


class PostsRepositoryImpl @Inject constructor(
    private val postsApiService: PostsApiService
) : PostsRepository {
    override suspend fun getPosts(): List<Post> {
        return postsApiService.getPosts()
    }
}