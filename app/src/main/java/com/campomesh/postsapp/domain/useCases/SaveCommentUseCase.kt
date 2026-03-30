package com.campomesh.postsapp.domain.useCases

import android.util.Log
import com.campomesh.postsapp.domain.models.Comment
import com.campomesh.postsapp.domain.repositories.CommentsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveCommentUseCase @Inject constructor(private val commentsRepository: CommentsRepository) {
    suspend operator fun invoke(comment: String, postId: Int): Boolean =
        try {
            commentsRepository.saveComment(
                Comment(
                    comment = comment,
                    postId = postId
                )
            ) != -1L
        } catch (exception: Exception) {
            Log.d("SaveCommentUseCase", exception.message ?: "")
            false
        }
}