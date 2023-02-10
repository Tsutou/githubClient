package com.example.githubClient.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var repositoryId: String
    init {
        repositoryId = savedStateHandle.get<String>("repositoryId") ?: ""
    }
}
