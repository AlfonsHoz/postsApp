package com.campomesh.postsapp.domain.useCases

import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavePostsUseCase @Inject constructor(private val postsRepository: PostsRepository) {
    suspend operator fun invoke(posts: List<Post>): Boolean = postsRepository.savePosts(posts)
}