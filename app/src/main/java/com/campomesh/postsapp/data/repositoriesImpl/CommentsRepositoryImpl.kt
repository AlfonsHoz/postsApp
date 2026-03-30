package com.campomesh.postsapp.data.repositoriesImpl

import com.campomesh.postsapp.data.database.dao.CommentDao
import com.campomesh.postsapp.data.database.entities.CommentsEntity
import com.campomesh.postsapp.domain.models.Comment
import com.campomesh.postsapp.domain.repositories.CommentsRepository
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val commentDao: CommentDao
) : CommentsRepository {
    override suspend fun getCommentsForPost(postId: Int): List<Comment> {
        return commentDao.getCommentsForPost(postId).map { it.toComment() }
    }

    override suspend fun saveComment(comment: Comment): Long {
        return commentDao.insertComment(comment.toDbComment())
    }

}