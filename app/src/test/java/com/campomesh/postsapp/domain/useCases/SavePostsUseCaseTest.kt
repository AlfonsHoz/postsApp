package com.campomesh.postsapp.domain.useCases

import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SavePostsUseCaseTest {
    private val repository = mockk<PostsRepository>()
    private val savePostsUseCase = SavePostsUseCase(repository)

    @Test
    fun `returns true when repository returns true from saving posts`() = runTest{
        val posts = listOf(
            Post(1, 1, "title1", "body1"),
            Post(2, 2, "title2", "body2")
        )
        coEvery { repository.savePosts(posts) } returns true

        val result = savePostsUseCase(posts)

        assert(result)
    }

    @Test
    fun `returns false if repository returns false from saving posts`() = runTest{
        coEvery { repository.savePosts(any()) } returns false

        val result = savePostsUseCase(listOf())

        assert(!result)
    }
}