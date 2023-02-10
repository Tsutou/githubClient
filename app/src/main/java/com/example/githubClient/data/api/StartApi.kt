package com.example.githubClient.data.api

interface StarApi {
    suspend fun addStar(repositoryId: String)
    suspend fun removeStar(repositoryId: String)
}
