package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.entities.Repository
import co.feip.fefu2025.domain.repository.RepositoryRepository

class GetRepositoriesUseCase(private val repository: RepositoryRepository) {
    fun getAll() = repository.getRepositories()
    fun getStarred() = repository.getStarredRepositories()
}
