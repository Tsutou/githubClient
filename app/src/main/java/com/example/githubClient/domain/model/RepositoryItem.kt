package com.example.githubClient.domain.model

data class RepositoryItem(
    val id : String,
    val name: String,
    val description: String,
    val owner : String,
    val stars: Int,
    val hasStarred: Boolean,
)
