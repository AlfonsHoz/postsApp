package com.campomesh.postsapp.domain.models

import com.campomesh.postsapp.data.database.entities.PostEntity

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
){
    fun toDbPost(): PostEntity = PostEntity(id,userId, title, body)
}