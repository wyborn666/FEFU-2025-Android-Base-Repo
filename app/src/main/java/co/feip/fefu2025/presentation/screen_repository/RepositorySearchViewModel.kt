package co.feip.fefu2025.presentation.screen_repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Repository
import co.feip.fefu2025.domain.use_cases.GetRepositoriesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RepositorySearchViewModel(
    private val useCase: GetRepositoriesUseCase
) : ViewModel() {

    private val _repositories = MutableStateFlow<List<Repository>>(emptyList())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _searchResults = MutableStateFlow<List<Repository>>(emptyList())
    val searchResults: StateFlow<List<Repository>> = _searchResults

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    private val _errorEvent = MutableSharedFlow<String>()

    init {
        viewModelScope.launch {
            try {
                _repositories.value = useCase.getAll()
            } catch (e: Exception) {
                _errorEvent.emit(e.message ?: "Ошибка загрузки репозиториев")
            }
        }

        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    flow {
                        if (query.isBlank()) {
                            emit(emptyList())
                            return@flow
                        }
                        _isSearching.value = true
                        delay(500)
                        val filtered = _repositories.value.filter {
                            it.username.contains(query, ignoreCase = true)
                        }
                        emit(filtered)
                    }
                }
                .catch { e -> _errorEvent.emit(e.message ?: "Ошибка поиска") }
                .collect { results ->
                    _searchResults.value = results
                    _isSearching.value = false
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}
class RepositorySearchViewModelFactory(
    private val useCase: GetRepositoriesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositorySearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RepositorySearchViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
