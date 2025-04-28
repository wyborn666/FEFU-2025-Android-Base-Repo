package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.entities.RepositoryCard

interface RepositoryCardRepository {
    suspend fun getRepositoryCard(): RepositoryCard
}