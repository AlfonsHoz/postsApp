package com.campomesh.postsapp.data.repositoriesImpl

import com.campomesh.postsapp.data.database.dao.PostsDao
import com.campomesh.postsapp.data.datasources.PostsApiService
import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsApiService: PostsApiService,
    private val postsDao: PostsDao
) : PostsRepository {
    override suspend fun getPosts(): List<Post> {
        return postsApiService.getPosts().map { it.toPost() }
    }

    override suspend fun savePosts(posts: List<Post>): Boolean {
        return postsDao.insertAll(posts.map { it.toDbPost() }).all { it != -1L }
    }

    override suspend fun getPost(id: Int): Post {
        return postsDao.getPostById(id).toPost()
    }
}