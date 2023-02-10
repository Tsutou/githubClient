package com.example.githubClient.di

import com.example.githubClient.data.api.ApolloSearchApi
import com.example.githubClient.data.api.ApolloStarApi
import com.example.githubClient.data.api.SearchApi
import com.example.githubClient.data.api.StarApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {
    @Singleton
    @Binds
    abstract fun provideSearchApi(searchApi: ApolloSearchApi): SearchApi

    @Singleton
    @Binds
    abstract fun provideStarApi(starApi: ApolloStarApi): StarApi
}
