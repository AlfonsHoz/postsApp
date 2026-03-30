package com.campomesh.postsapp.domain.useCases

import com.campomesh.postsapp.domain.models.Post
import com.campomesh.postsapp.domain.repositories.PostsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SearchPostsUseCaseTest {
    private val repository = mockk<PostsRepository>()
    private val searchPostsUseCase = SearchPostsUseCase(repository)

    @Test
    fun `should return valid posts to search by id`() = runTest {
        val posts = listOf(
            Post(1, 1, "title1", "body1"),
            Post(11, 2, "title2", "body2"),
            Post(2, 3, "title3", "body3"),
            Post(22, 4, "title4", "body4"),
        )

        coEvery { repository.getPostByQuery("1") } returns posts.filter { (it.id).toString().contains('1') }
        coEvery { repository.getPostByQuery("2") } returns posts.filter { (it.id).toString().contains('2') }

        val  result1 = searchPostsUseCase("1")
        val  result2 = searchPostsUseCase("2")

        assertEquals(2, result1.size)
        assertEquals(2, result2.size)
        assertEquals(1, result1.first().id)
        assertEquals(2, result2.first().id)
    }

    @Test
    fun `should return empty list when query not matches any post`() = runTest{
        val posts = listOf(
            Post(1, 1, "title1", "body1"),
            Post(11, 2, "title2", "body2"),
            Post(2, 3, "title3", "body3"),
            Post(22, 4, "title4", "body4"),
        )

        coEvery { repository.getPostByQuery(any()) } returns emptyList()

        val result = searchPostsUseCase("5")

        assertEquals(0, result.size)
    }

    @Test
    fun `should return empty list when repository throws`() = runTest {
        coEvery { repository.getPostByQuery(any()) } throws Exception()

        val result = searchPostsUseCase("5")

        assertEquals(0, result.size)
    }

}