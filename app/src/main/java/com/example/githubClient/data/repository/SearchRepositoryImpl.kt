package com.example.githubClient.data.repository

import com.example.githubClient.data.api.ApolloSearchApi
import com.example.githubClient.domain.model.PageItems
import com.example.githubClient.domain.model.RepositoryItem
import com.example.githubClient.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    val searchApi: ApolloSearchApi
) : SearchRepository {
    override suspend fun fetchRepositories(
        query: String,
        endCursor: String?
    ): PageItems<RepositoryItem> {
        return searchApi.fetchRepositories(query, endCursor)
    }
}
