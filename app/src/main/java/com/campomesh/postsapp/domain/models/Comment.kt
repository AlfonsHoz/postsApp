package com.campomesh.postsapp.domain.models

import com.campomesh.postsapp.data.database.entities.CommentsEntity

data class Comment(
    val id: Int = 0,
    val postId: Int,
    val comment: String
) {
    fun toDbComment(): CommentsEntity = CommentsEntity(id, postId, comment)
}
