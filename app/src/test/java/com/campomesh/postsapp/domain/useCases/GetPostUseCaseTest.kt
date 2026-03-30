package com.campomesh.postsapp.domain.useCases

import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPostUseCaseTest {
    private val repository = mockk<PostsRepository>()
    private val getPostUseCase = GetPostUseCase(repository)

    @Test
    fun `returns post when found`() = runTest {
        val post = Post(1, 1, "title", "body")
        coEvery { repository.getPost(1) } returns post

        val result = getPostUseCase(1)

        assertNotNull(result)
        assertEquals("title", result?.title)
        assertEquals(1, result?.id)
    }

    @Test
    fun `returns null when not found`() = runTest {
        coEvery { repository.getPost(0) } returns null

        val result = getPostUseCase(1)

        assertNull(result)
    }

    @Test
    fun `return null when repository throws`() = runTest {
        coEvery { repository.getPost(any()) } throws Exception("Error")

        val result = getPostUseCase(2)

        assertNull(result)
    }
}