package com.campomesh.postsapp.data.models

import com.campomesh.postsapp.domain.models.Post
import com.google.gson.annotations.SerializedName

data class ApiPost(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
) {
    fun toPost(): Post = Post(id, userId, title, body)
}
