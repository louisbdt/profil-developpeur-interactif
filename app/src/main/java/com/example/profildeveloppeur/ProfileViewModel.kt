package com.example.profildeveloppeur

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProfileUiState(
    val projects: List<ProjectUi> = emptyList(),
    val searchQuery: String = "",
    val favoriteProjectIds: List<Int> = emptyList()
)

class ProfileViewModel : ViewModel() {

    private val allProjects: List<ProjectUi> = sampleProjects()

    private val _uiState = MutableStateFlow(
        ProfileUiState(projects = allProjects)
    )
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private fun filterProjects(searchQuery: String): List<ProjectUi> {
        return allProjects.filter { project ->
            project.title.contains(searchQuery, ignoreCase = true) ||
                project.technologies.any { technology -> technology.contains(searchQuery, ignoreCase = true) } ||
                project.description.contains(searchQuery, ignoreCase = true)
        }
    }

    fun onSearchQueryChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = newValue,
                projects = filterProjects(newValue)
            )
        }
    }

    fun onFavoriteClick(projectId: Int) {
        _uiState.update { currentState ->
            val newFavoriteIds = if (currentState.favoriteProjectIds.contains(projectId)) {
                currentState.favoriteProjectIds - projectId
            } else {
                currentState.favoriteProjectIds + projectId
            }

            currentState.copy(
                favoriteProjectIds = newFavoriteIds
            )
        }
    }
}
