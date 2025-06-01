package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.entities.Repository

interface RepositoryRepository {
    fun getRepositories(): List<Repository>
    fun getStarredRepositories(): List<Repository>
}
