package com.campomesh.postsapp.domain.useCases

import android.util.Log
import com.campomesh.postsapp.domain.models.Comment
import com.campomesh.postsapp.domain.repositories.CommentsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCommentsUseCase @Inject constructor(private val commentsRepository: CommentsRepository) {
    suspend operator fun invoke(postId: Int): List<Comment> =
        try {
            commentsRepository.getCommentsForPost(postId)
        } catch (exception: Exception) {
            listOf()
        }
}