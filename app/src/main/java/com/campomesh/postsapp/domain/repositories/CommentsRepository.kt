package com.campomesh.postsapp.domain.repositories

import com.campomesh.postsapp.domain.models.Comment

interface CommentsRepository {
    suspend fun getCommentsForPost(postId: Int): List<Comment>

    suspend fun saveComment(comment: Comment): Long
}