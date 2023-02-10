package com.example.githubClient.ui.search

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubClient.R
import com.example.githubClient.domain.model.RepositoryItem
import com.example.githubClient.ui.LocalNavigator
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val navigator = LocalNavigator.current
    val searchUiState by viewModel.uiState.collectAsState()

    val listState = rememberLazyListState()
    InfiniteListHandler(listState = listState, onLoadNext = { viewModel.onLoadNext() })

    LazyColumn(state = listState) {
        stickyHeader {
            SearchTextBox(
                Modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxWidth()
                    .padding(8.dp),
                searchUiState
            ) { input ->
                viewModel.onSearchTextChange(input)
            }
        }

        val isEmptyResult = searchUiState.searchItems?.isEmpty() == true
        if (isEmptyResult) {
            item {
                SearchEmptyBox(modifier = Modifier.fillMaxSize().padding(16.dp))
            }
        } else {
            searchUiState.searchItems?.items?.forEach { repositoryItem ->
                item {
                    SearchRepositoryItem(
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        repositoryItem = repositoryItem,
                        onRepositoryItemClick = { item -> navigator.navigateToDetail(item.id) },
                        onToggleStar = { item -> viewModel.onToggleStar(item) }
                    )
                }
            }
        }

        if (searchUiState.isLoading) {
            item {
                SearchPageLoading(
                    Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun SearchTextBox(
    modifier: Modifier,
    searchUiState: SearchUiState,
    onSearchTextChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        singleLine = true,
        value = searchUiState.userInput,
        onValueChange = onSearchTextChange,
        label = { Text(text = stringResource(R.string.search_text_box_label)) }
    )
}

@Composable
private fun SearchEmptyBox(
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(128.dp)
                    .padding(vertical = 24.dp),
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
            Text(
                stringResource(R.string.search_empty_text),
                style = MaterialTheme.typography.h6,
            )
            Text(
                stringResource(R.string.search_empty_description),
                style = MaterialTheme.typography.body1,
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SearchRepositoryItem(
    modifier: Modifier,
    repositoryItem: RepositoryItem,
    onRepositoryItemClick: (RepositoryItem) -> Unit,
    onToggleStar: (RepositoryItem) -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = { onRepositoryItemClick(repositoryItem) }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                repositoryItem.name,
                style = MaterialTheme.typography.h6,
            )
            if (repositoryItem.description.isNotEmpty()) {
                Text(
                    repositoryItem.description,
                    style = MaterialTheme.typography.body1,
                )
            }
            Text(repositoryItem.owner, style = MaterialTheme.typography.body2)
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconToggleButton(
                    checked = repositoryItem.hasStarred,
                    onCheckedChange = {
                        onToggleStar(repositoryItem)
                    }
                ) {
                    val tint = animateColorAsState(
                        if (repositoryItem.hasStarred) Color(0xFFFFC600) else Color(0xFFB0BEC5),
                        animationSpec = tween(
                            durationMillis = 1000,
                            delayMillis = 40,
                            easing = LinearOutSlowInEasing
                        )
                    )
                    Icon(
                        Icons.Filled.Grade,
                        contentDescription = "null",
                        tint = tint.value
                    )
                }

                Text(repositoryItem.stars.toString(), style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Composable
private fun SearchPageLoading(
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

//https://dev.to/luismierez/infinite-lazycolumn-in-jetpack-compose-44a4
@Composable
fun InfiniteListHandler(
    listState: LazyListState,
    buffer: Int = 2,
    onLoadNext: () -> Unit
) {
    val isLoadNext = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsCount = layoutInfo.totalItemsCount
            val visibleItemsCount = layoutInfo.visibleItemsInfo.size
            val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            visibleItemsCount < totalItemsCount &&
                    lastVisibleItemIndex >= (totalItemsCount - 1 - buffer)
        }
    }

    LaunchedEffect(isLoadNext) {
        snapshotFlow { isLoadNext.value }
            .filter { it }
            .collect {
                onLoadNext()
            }
    }
}