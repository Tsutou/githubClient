package com.example.githubClient.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.network.okHttpClient
import com.example.githubClient.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApolloModule {
    private const val API_BASE_URL = "https://api.github.com/graphql"

    private const val HEADER_AUTHORIZATION = "Authorization"
    private const val HEADER_AUTHORIZATION_BEARER = "Bearer"

    @Singleton
    @Provides
    fun provideOkHttpBuilder(): OkHttpClient {
        return OkHttpClient()
    }

    @Singleton
    @Provides
    fun provideApolloClient(client: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(API_BASE_URL)
            .fetchPolicy(FetchPolicy.NetworkOnly)
            .addHttpHeader(
                HEADER_AUTHORIZATION,
                "$HEADER_AUTHORIZATION_BEARER ${BuildConfig.GITHUB_OAUTH_KEY}"
            )
            .normalizedCache(MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024))
            .okHttpClient(client)
            .build()
    }
}
