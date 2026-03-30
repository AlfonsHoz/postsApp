package com.campomesh.postsapp.data.repositoriesImpl

import com.campomesh.postsapp.data.database.dao.CommentDao
import com.campomesh.postsapp.data.database.entities.CommentsEntity
import com.campomesh.postsapp.domain.models.Comment
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CommentsRepositoryImplTest {
    private val dao = mockk<CommentDao>(relaxed = true)
    private val repository = CommentsRepositoryImpl(dao)

    @Test
    fun `getCommentsForPost - should return a list of comments mapped from DbComment`() = runTest {
        val dbComments = listOf(
            CommentsEntity(1, 1, "Comentario 1"),
            CommentsEntity(2, 1, "Comentario 2"),
        )

        coEvery { dao.getCommentsForPost(any()) } returns dbComments

        val result = repository.getCommentsForPost(1)

        assertEquals(dbComments.first().comment, result.first().comment)
        assertEquals(result[0].postId, result[1].postId)
    }

    @Test
    fun `saveComment - should return a long value on save comment`() = runTest {
        coEvery { dao.insertComment(any()) } returns 1L

        val result = repository.saveComment(Comment(1, 1, "Comentario 1"))

        assertEquals(true, result != -1L)
    }
}