package com.campomesh.postsapp.data.repositoriesImpl

import com.campomesh.postsapp.data.database.dao.PostsDao
import com.campomesh.postsapp.data.database.entities.PostEntity
import com.campomesh.postsapp.data.datasources.PostsApiService
import com.campomesh.postsapp.data.models.ApiPost
import com.campomesh.postsapp.domain.models.Post
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PostsRepositoryImplTest {
    private val api = mockk<PostsApiService>()
    private val dao = mockk<PostsDao>(relaxed = true)
    private val repository = PostsRepositoryImpl(api, dao)

    @Test
    fun `getPosts - should return a lis of posts mapped form ApiPost`() = runTest {
        val apiPosts = listOf(ApiPost(1, 1, "title", "body"))
        coEvery { api.getPosts() } returns apiPosts

        val result = repository.getPosts()

        assert(result.isNotEmpty())
        assertEquals(apiPosts.first().id, result.first().id)
    }

    @Test
    fun `getPostByQuery - should return a list of posts mapped from DbPost`() = runTest {
        val dbPosts = listOf(PostEntity(1, 1, "title", "body"))
        coEvery { dao.getPostsByQuery(any()) } returns dbPosts

        val result = repository.getPostByQuery("1")

        assert(result.isNotEmpty())
        assertEquals(dbPosts.first().id, result.first().id)
    }

    @Test
    fun `savePosts - should return tru when dao saves posts`() = runTest {
        val posts = listOf<PostEntity>(PostEntity(1, 1, "title", "body"))

        coEvery { dao.insertAll(any()) } returns listOf(1L)

        val result = repository.savePosts(listOf(Post(1, 1, "title", "body")))

        assert(result)
    }

    @Test
    fun `getPost - should return a post mapped from DbPost`() = runTest {
        val dbPost = PostEntity(1, 1, "title", "body")
        coEvery { dao.getPostById(any()) }  returns dbPost

        val result = repository.getPost(1)

        assertEquals(dbPost.id, result.id)
    }
}