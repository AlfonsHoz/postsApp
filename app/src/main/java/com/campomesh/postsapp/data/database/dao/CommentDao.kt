package com.campomesh.postsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.campomesh.postsapp.data.database.entities.CommentsEntity

@Dao
interface CommentDao {
    @Query("SELECT * FROM comments WHERE postId = :postId")
    suspend fun getCommentsForPost(postId: Int): List<CommentsEntity>

    @Query("DELETE FROM comments WHERE postId = :postId")
    suspend fun deleteCommentsForPost(postId: Int)

    @Insert
    suspend fun insertComment(comment: CommentsEntity): Long
}