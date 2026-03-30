package com.campomesh.postsapp.domain.useCases

import android.util.Log
import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchPostsUseCase @Inject constructor(private val postsRepository: PostsRepository) {
    suspend operator fun invoke(query: String): List<Post> =
        try {
            postsRepository.getPostByQuery(query)
        } catch (exception: Exception) {
            emptyList()
        }
}