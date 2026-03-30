package com.campomesh.postsapp.data.di

import com.campomesh.postsapp.core.database.AppDatabase
import com.campomesh.postsapp.data.database.dao.CommentDao
import com.campomesh.postsapp.data.database.dao.PostsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun providePostsDao(database: AppDatabase): PostsDao =
        database.postsDao()

    @Provides
    @Singleton
    fun provideCommentDao(database: AppDatabase): CommentDao =
        database.commentsDao()

}