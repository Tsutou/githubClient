package com.example.githubClient.data.repository

import com.example.githubClient.data.api.ApolloStarApi
import com.example.githubClient.domain.repository.StarRepository
import javax.inject.Inject

class StarRepositoryImpl @Inject constructor(
    val starApi: ApolloStarApi
) : StarRepository {
    override suspend fun addStar(repositoryId: String) {
        starApi.addStar(repositoryId)
    }

    override suspend fun removeStar(repositoryId: String) {
        starApi.removeStar(repositoryId)
    }
}
