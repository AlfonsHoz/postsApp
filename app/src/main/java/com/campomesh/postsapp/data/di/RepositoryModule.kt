package com.campomesh.postsapp.data.di

import com.campomesh.postsapp.data.repositoriesImpl.CommentsRepositoryImpl
import com.campomesh.postsapp.data.repositoriesImpl.PostsRepositoryImpl
import com.campomesh.postsapp.domain.repositories.CommentsRepository
import com.campomesh.postsapp.domain.repositories.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostsRepository(
        postsRepositoryImpl: PostsRepositoryImpl
    ): PostsRepository

    @Binds
    @Singleton
    abstract fun bindCommentsRepository(
        commentsRepositoryImpl: CommentsRepositoryImpl
    ): CommentsRepository
}