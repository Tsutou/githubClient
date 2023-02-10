package com.example.githubClient.di

import com.example.githubClient.domain.repository.SearchRepository
import com.example.githubClient.data.repository.SearchRepositoryImpl
import com.example.githubClient.data.repository.StarRepositoryImpl
import com.example.githubClient.domain.repository.StarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideSearchRepository(repository: SearchRepositoryImpl): SearchRepository

    @Singleton
    @Binds
    abstract fun provideStarRepository(repository: StarRepositoryImpl): StarRepository
}
