package com.campomesh.postsapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.campomesh.postsapp.data.database.dao.CommentDao
import com.campomesh.postsapp.data.database.dao.PostsDao
import com.campomesh.postsapp.data.database.entities.CommentsEntity
import com.campomesh.postsapp.data.database.entities.PostEntity

@Database(entities = [PostEntity::class, CommentsEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun postsDao(): PostsDao

    abstract fun commentsDao(): CommentDao
}