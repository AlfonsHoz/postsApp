package com.campomesh.postsapp.domain.useCases

import android.util.Log
import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPostUseCase @Inject constructor(private val postsRepository: PostsRepository) {
    suspend operator fun invoke(id: Int): Post? = try {
        postsRepository.getPost(id)
    } catch (exception: Exception) {
        Log.d("GetPostUseCase", "Error getting post", exception)
        null
    }
}