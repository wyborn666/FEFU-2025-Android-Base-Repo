package co.feip.fefu2025.presentation.screen_repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Repository
import co.feip.fefu2025.domain.use_cases.GetRepositoriesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StarredRepositoriesViewModel(
    private val useCase: GetRepositoriesUseCase
) : ViewModel() {

    private val _starred = MutableStateFlow<List<Repository>>(emptyList())
    val starred: StateFlow<List<Repository>> get() = _starred

    private val _isLoadingStars = MutableStateFlow(false)
    val isLoadingStars: StateFlow<Boolean> get() = _isLoadingStars

    private val _errorMessageStars = MutableStateFlow<String?>(null)
    val errorMessageStars: StateFlow<String?> get() = _errorMessageStars

    private var isFirstStarsLoad = true

    fun loadStarredRepositories() {
        if (_starred.value.isNotEmpty()) return
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
}
class StarredRepositoriesViewModelFactory(
    private val useCase: GetRepositoriesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StarredRepositoriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StarredRepositoriesViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
