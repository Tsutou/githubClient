package com.example.githubClient.data.api

import com.apollographql.apollo3.ApolloClient
import com.example.githubClient.SearchRepositoriesQuery
import com.example.githubClient.data.api.mapper.SearchMapper
import com.example.githubClient.domain.model.PageItems
import com.example.githubClient.domain.model.RepositoryItem
import timber.log.Timber
import javax.inject.Inject

class ApolloSearchApi @Inject constructor(
    private val apolloClient: ApolloClient
) : SearchApi {

    override suspend fun fetchRepositories(
        query: String,
        endCursor: String?
    ): PageItems<RepositoryItem> {
        val data = apolloClient.query(
            SearchRepositoriesQuery(
                query = query,
                number_of_repos = DEFAULT_PAGING_LIMIT,
                cursor = endCursor
            )
        ).execute().data

        if (data == null) {
            val error = IllegalStateException("response data is null.")
            Timber.e(error)
            throw error
        }

        val edges = data.search.edges ?: emptyList()

        return PageItems(
            items = edges.mapNotNull {
                it?.node?.onRepository
            }.map { repo ->
                SearchMapper.map(repo)
            },
            totalCount = data.search.repositoryCount,
            startCursor = PageItems.PagingCursor(
                value = data.search.pageInfo.startCursor ?: "",
                isHalfway = data.search.pageInfo.hasPreviousPage
            ),
            endCursor = PageItems.PagingCursor(
                value = data.search.pageInfo.endCursor ?: "",
                isHalfway = data.search.pageInfo.hasNextPage
            )
        )
    }

    companion object {
        const val DEFAULT_PAGING_LIMIT = 20
    }
}
