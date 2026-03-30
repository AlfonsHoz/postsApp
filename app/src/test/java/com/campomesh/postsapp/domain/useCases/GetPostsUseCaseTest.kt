package com.campomesh.postsapp.domain.useCases

import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest

import org.junit.Test

class GetPostsUseCaseTest {
    private val repository = mockk<PostsRepository>()
    private val getPostsUseCase = GetPostsUseCase(repository)

    @Test
    fun `returns posts when repository returns them`() = runTest {
        val posts = listOf(
            Post(1, 1, "title1", "body1"),
            Post(2, 2, "title2", "body2")
        )
        coEvery { repository.getPosts() } returns posts

        val result = getPostsUseCase()

        assertEquals(posts, result)
    }

    @Test
    fun `returns empty list when repository fails to retrieve posts`() = runTest {
        coEvery { repository.getPosts() } throws Exception()

        val result = getPostsUseCase()

        assertTrue(result.isEmpty())
    }
}