package com.example.githubClient.data.api.mapper

import com.example.githubClient.SearchRepositoriesQuery
import com.example.githubClient.domain.model.RepositoryItem

object SearchMapper {
    fun map(response: SearchRepositoriesQuery.OnRepository): RepositoryItem {
        return RepositoryItem(
            id = response.id,
            name = response.name,
            description = response.description ?: "",
            owner = response.owner.login,
            stars = response.stargazerCount,
            hasStarred = response.viewerHasStarred
        )
    }
}
