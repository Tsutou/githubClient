package com.example.githubClient.ui.search

import com.example.githubClient.domain.model.PageItems
import com.example.githubClient.domain.model.RepositoryItem

data class SearchUiState(
    val isLoading: Boolean = false,
    val userInput: String = "",
    val searchItems: PageItems<RepositoryItem>? = null,
    val isToggling: Boolean = false,
) {
    companion object {
        val EMPTY = SearchUiState()
    }
}
