package com.campomesh.postsapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.campomesh.postsapp.data.database.dao.PostsDao
import com.campomesh.postsapp.data.database.entities.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun postsDao(): PostsDao
}