package co.feip.fefu2025.presentation.screen_repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.RepositoryCard
import co.feip.fefu2025.domain.use_cases.GetRepositoryCardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class RepositoryViewModel(
    private val getRepositoryCardUseCase: GetRepositoryCardUseCase
) : ViewModel() {

    private val _repositoryCard = MutableStateFlow<RepositoryCard?>(null)
    val repositoryCard: StateFlow<RepositoryCard?> get() = _repositoryCard

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private var isFirstLoad = true

    init {
        loadCard()
    }

    fun loadCard() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                if (isFirstLoad) {
                    delay(3000)
                    throw Exception("Не удалось загрузить карточку репозитория!")
                }

                delay(2000)

                _repositoryCard.value = getRepositoryCardUseCase()
                isFirstLoad = false

            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun retryLoad() {
        isFirstLoad = false
        loadCard()
    }
}
