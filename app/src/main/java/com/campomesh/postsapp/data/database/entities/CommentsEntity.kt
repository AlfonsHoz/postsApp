package com.campomesh.postsapp.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.campomesh.postsapp.domain.models.Comment

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = PostEntity::class,
            parentColumns = ["id"],
            childColumns = ["postId"],
        )
    ],
    indices = [Index("postId")]
)
data class CommentsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val postId: Int,
    val comment: String
) {
    fun toComment(): Comment = Comment(id, postId, comment)
}
