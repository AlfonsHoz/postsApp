package com.campomesh.postsapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.campomesh.postsapp.domain.models.Post

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    fun toPost(): Post = Post(id, userId, title, body)
}
