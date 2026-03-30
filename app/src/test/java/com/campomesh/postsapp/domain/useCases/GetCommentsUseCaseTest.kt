package com.campomesh.postsapp.domain.useCases

import com.campomesh.postsapp.domain.models.Comment
import com.campomesh.postsapp.domain.repositories.CommentsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCommentsUseCaseTest {
    private val repository = mockk<CommentsRepository>()
    private val getCommentsUseCase = GetCommentsUseCase(repository)

    @Test
    fun `should return comments when repository returns them`() = runTest {
        val comments = listOf(
            Comment(1, 1, "Comentario 1"),
            Comment(2, 1, "Comentario 2"),
            Comment(2, 1, "Comentario 3"),
        )

        coEvery { repository.getCommentsForPost(1) } returns comments

        val result = getCommentsUseCase(1)

        assertEquals(3, result.size)
        assertEquals(1, result.first().postId)
    }

    @Test
    fun `should return empty list when repository fails to retrieve comments`() = runTest {
        coEvery { repository.getCommentsForPost(any()) } throws Exception()

        val result = getCommentsUseCase(1)

        assertEquals(0, result.size)
    }
}