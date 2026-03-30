package com.campomesh.postsapp.data.datasources

import com.campomesh.postsapp.data.models.ApiPost
import retrofit2.http.GET

interface PostsApiService {
    @GET("/posts")
    suspend fun getPosts(): List<ApiPost>
}