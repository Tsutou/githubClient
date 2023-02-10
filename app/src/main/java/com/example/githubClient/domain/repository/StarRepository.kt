package com.example.githubClient.domain.repository

interface StarRepository {
    suspend fun addStar(repositoryId: String)
    suspend fun removeStar(repositoryId: String)
}
