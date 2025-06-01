package co.feip.fefu2025.domain.entities

data class Repository(
    val username: String,
    val description: String,
    val stars: String,
    val forks: String,
    val iconResId: Int,
    val repoId: String
)
