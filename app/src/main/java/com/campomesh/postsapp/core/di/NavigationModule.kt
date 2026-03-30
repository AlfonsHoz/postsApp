package com.campomesh.postsapp.core.di

import com.campomesh.postsapp.core.navigation.AppNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    @Singleton
    fun provideAppNavigator(): AppNavigator = AppNavigator()
}