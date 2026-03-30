package com.campomesh.postsapp.domain.useCases

import com.campomesh.postsapp.data.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPostsUseCase @Inject constructor(private val postsRepository: PostsRepository) {
    suspend operator fun invoke(): List<Post> = postsRepository.getPosts()
}