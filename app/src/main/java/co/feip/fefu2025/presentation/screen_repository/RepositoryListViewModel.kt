package co.feip.fefu2025.presentation.screen_repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Repository
import co.feip.fefu2025.domain.use_cases.GetRepositoriesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RepositoryListViewModel(
    private val useCase: GetRepositoriesUseCase,
) : ViewModel() {

    private val _repositories = MutableStateFlow<List<Repository>>(emptyList())
    val repositories: StateFlow<List<Repository>> get() = _repositories

    private val _starred = MutableStateFlow<List<Repository>>(emptyList())
    val starred: StateFlow<List<Repository>> get() = _starred

    private val _isLoadingMain = MutableStateFlow(true)
    val isLoadingMain: StateFlow<Boolean> get() = _isLoadingMain

    private val _errorMessageMain = MutableStateFlow<String?>(null)
    val errorMessageMain: StateFlow<String?> get() = _errorMessageMain

    private val _isLoadingStars = MutableStateFlow(false)
    val isLoadingStars: StateFlow<Boolean> get() = _isLoadingStars

    private val _errorMessageStars = MutableStateFlow<String?>(null)
    val errorMessageStars: StateFlow<String?> get() = _errorMessageStars

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _searchResults = MutableStateFlow<List<Repository>>(emptyList())
    val searchResults: StateFlow<List<Repository>> = _searchResults

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    private var isFirstMainLoad = true
    private var isFirstStarsLoad = true

    init {
        loadMainRepositories()
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
                .catch { e ->
                    _searchResults.value = emptyList()
                }
                .collect { results ->
                    _searchResults.value = results
                    _isSearching.value = false
                }
        }
    }
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    private fun loadMainRepositories() {
        viewModelScope.launch {
            _isLoadingMain.value = true
            _errorMessageMain.value = null

            try {
                delay(3000)
                if (isFirstMainLoad) {
                    isFirstMainLoad = false
                    throw Exception("Ошибка загрузки главного экрана")
                }
                _repositories.value = useCase.getAll()
                _starred.value = useCase.getStarred()
            } catch (e: Exception) {
                _errorMessageMain.value = e.message
            } finally {
                _isLoadingMain.value = false
            }
        }
    }

    fun loadStarredRepositories() {
        viewModelScope.launch {
            _isLoadingStars.value = true
            _errorMessageStars.value = null

            try {
                delay(3000)
                if (isFirstStarsLoad) {
                    isFirstStarsLoad = false
                    throw Exception("Ошибка загрузки репозиториев")
                }
                _starred.value = useCase.getStarred()
            } catch (e: Exception) {
                _errorMessageStars.value = e.message
            } finally {
                _isLoadingStars.value = false
            }
        }
    }

    fun retryMainLoad() {
        loadMainRepositories()
    }

}
