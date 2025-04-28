package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.repository.RepositoryRepository

class GetRepositoriesUseCase(private val repository: RepositoryRepository) {
    suspend fun getAll() = repository.getRepositories()
    suspend fun getStarred() = repository.getStarredRepositories()
}
