package com.example.githubClient.data.api

import com.apollographql.apollo3.ApolloClient
import com.example.githubClient.AddStarMutation
import com.example.githubClient.RemoveStarMutation
import timber.log.Timber
import javax.inject.Inject

class ApolloStarApi @Inject constructor(
    private val apolloClient: ApolloClient
)  : StarApi {
    override suspend fun addStar(repositoryId: String) {
        val mutation = apolloClient.mutation(
            AddStarMutation(repositoryId = repositoryId)
        )
        val data = mutation.execute().data
        if (data == null) {
            val error = IllegalStateException("response data is null.")
            Timber.e(error)
            throw error
        }
    }

    override suspend fun removeStar(repositoryId: String) {
        val mutation = apolloClient.mutation(
            RemoveStarMutation(repositoryId = repositoryId)
        )
        val data = mutation.execute().data
        if (data == null) {
            val error = IllegalStateException("response data is null.")
            Timber.e(error)
            throw error
        }
    }
}
