package com.example.githubClient.domain.repository

import com.example.githubClient.domain.model.PageItems
import com.example.githubClient.domain.model.RepositoryItem

interface SearchRepository {
    suspend fun fetchRepositories(
        query: String,
        endCursor: String?
    ): PageItems<RepositoryItem>
}
