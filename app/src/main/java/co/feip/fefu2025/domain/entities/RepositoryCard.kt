package co.feip.fefu2025.domain.entities

data class RepositoryCard(
    val username: String,
    val description: String,
    val languages: List<Language>,
    val createdDate: String,
    val stars: String,
    val forks: String,
    val iconResId: Int
)
