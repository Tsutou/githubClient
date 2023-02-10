package com.example.githubClient.data.api

import com.example.githubClient.domain.model.PageItems
import com.example.githubClient.domain.model.RepositoryItem

interface SearchApi {
    suspend fun fetchRepositories(
        query: String,
        endCursor: String?
    ): PageItems<RepositoryItem>
}
