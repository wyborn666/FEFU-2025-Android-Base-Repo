package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.repository.RepositoryCardRepository
import co.feip.fefu2025.domain.entities.RepositoryCard

class GetRepositoryCardUseCase(
    private val repository: RepositoryCardRepository
) {
    suspend operator fun invoke(): RepositoryCard {
        return repository.getRepositoryCard()
    }
}
