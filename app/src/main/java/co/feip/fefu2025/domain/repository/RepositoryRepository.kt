package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.entities.Repository

interface RepositoryRepository {
    suspend fun getRepositories(): List<Repository>
    suspend fun getStarredRepositories(): List<Repository>
}
