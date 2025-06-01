package co.feip.fefu2025.presentation.screen_repository

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import co.feip.fefu2025.domain.entities.Repository
import co.feip.fefu2025.domain.use_cases.GetRepositoriesUseCase

class RepositoryListViewModel(private val useCase: GetRepositoriesUseCase) : ViewModel() {
    private val _repositories = MutableStateFlow<List<Repository>>(emptyList())
    val repositories: StateFlow<List<Repository>> get() = _repositories

    private val _starred = MutableStateFlow<List<Repository>>(emptyList())
    val starred: StateFlow<List<Repository>> get() = _starred

    init {
        _repositories.value = useCase.getAll()
        _starred.value = useCase.getStarred()
    }
}
