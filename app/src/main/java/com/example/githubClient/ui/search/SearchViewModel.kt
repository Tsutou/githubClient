package com.example.githubClient.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubClient.domain.repository.SearchRepository
import com.example.githubClient.domain.model.PageItems
import com.example.githubClient.domain.model.RepositoryItem
import com.example.githubClient.domain.model.plus
import com.example.githubClient.domain.repository.StarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val starRepository: StarRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState.EMPTY)
    val uiState: StateFlow<SearchUiState> = _uiState

    private var searchJob : Job? = null

    init {
        onLoadInitial()
    }

    fun onSearchTextChange(value: String) {
        _uiState.value = _uiState.value.copy(
            searchItems = null,
            userInput = value
        )
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            onLoadInitial()
        }
    }

    private fun onLoadInitial() {
        viewModelScope.launch {
            runCatching {
                _uiState.value = _uiState.value.copy(
                    isLoading = true
                )
                searchRepository.fetchRepositories(
                    _uiState.value.userInput,
                    null
                )
            }.onSuccess { searchPageItems ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    searchItems = if (searchPageItems.items.isEmpty()) {
                        PageItems.empty()
                    } else {
                        searchPageItems
                    },
                )
            }.onFailure {
                Timber.e(it)
            }
        }
    }

    fun onLoadNext() {
        val pageItems = _uiState.value.searchItems
        if (pageItems == null || !pageItems.hasNext()) {
            return
        }

        viewModelScope.launch {
            runCatching {
                _uiState.value = _uiState.value.copy(
                    isLoading = true
                )
                searchRepository.fetchRepositories(
                    _uiState.value.userInput,
                    pageItems.endCursor.value
                )
            }.onSuccess { searchPageItems ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    searchItems = pageItems.plus(searchPageItems),
                )
            }.onFailure {
                Timber.e(it)
            }
        }
    }

    fun onToggleStar(repositoryItem: RepositoryItem) {
        viewModelScope.launch {
            if (uiState.value.isToggling) {
                return@launch
            }
            runCatching {
                _uiState.value = _uiState.value.copy(isToggling = true)
                val hasStar = repositoryItem.hasStarred
                if (hasStar) {
                    starRepository.removeStar(repositoryItem.id)
                } else {
                    starRepository.addStar(repositoryItem.id)
                }
                hasStar
            }.onSuccess { hasStar ->
                val pageItems = _uiState.value.searchItems ?: return@launch
                val items = pageItems.items
                val newItems = items.map { item ->
                    if (item.id == repositoryItem.id) {
                        item.copy(
                            stars = if (hasStar) item.stars - 1 else item.stars + 1,
                            hasStarred = !hasStar
                        )
                    } else {
                        item
                    }
                }
                _uiState.value = _uiState.value.copy(
                    isToggling = false,
                    searchItems = pageItems.copy(
                        items = newItems
                    )
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(isToggling = false)
                Timber.e(it)
            }
        }
    }
}
