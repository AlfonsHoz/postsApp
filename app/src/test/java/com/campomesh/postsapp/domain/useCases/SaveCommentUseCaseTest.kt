package com.campomesh.postsapp.domain.useCases

import com.campomesh.postsapp.domain.repositories.CommentsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SaveCommentUseCaseTest {
    private val repository = mockk<CommentsRepository>()
    private val saveCommentUseCase = SaveCommentUseCase(repository)

    @Test
    fun `Should return true on valid insert on repository`() = runTest {
        coEvery { repository.saveComment(any()) } returns 1L

        val result = saveCommentUseCase("Comentario 1", 1)

        assertEquals(true, result)
    }

    @Test
    fun `should return false when fails on save comment from repository`() = runTest {
        coEvery { repository.saveComment(any()) } throws Exception("Error")

        val result = saveCommentUseCase("Comentario 1", 1)

        assertEquals(false, result)
    }
}