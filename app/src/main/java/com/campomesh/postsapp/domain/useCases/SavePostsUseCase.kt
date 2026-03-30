package com.campomesh.postsapp.domain.useCases

import android.util.Log
import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavePostsUseCase @Inject constructor(private val postsRepository: PostsRepository) {
    suspend operator fun invoke(posts: List<Post>): Boolean = try {
        postsRepository.savePosts(posts)
    } catch (exception: Exception) {
        false
    }
}