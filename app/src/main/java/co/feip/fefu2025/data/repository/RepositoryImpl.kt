package co.feip.fefu2025.data

import co.feip.fefu2025.R
import co.feip.fefu2025.domain.entities.Repository
import co.feip.fefu2025.domain.repository.RepositoryRepository
import kotlinx.coroutines.delay

class RepositoryImpl : RepositoryRepository {
    override suspend fun getRepositories(): List<Repository> {
        delay(2000)
        return listOf(
            Repository("wyborn666", "My repository", "120", "32", R.drawable.me),
            Repository("AlexMarchu", "Teacher of the Pacific Design School", "3400", "320", R.drawable.icon_rep1),
            Repository("sxmurxy0", "Minecraft cheats and puzzles", "2750", "450", R.drawable.icon_rep2),
            Repository("mesenev", "developer and teacher from Vladivostok", "9999", "600", R.drawable.icon_rep3),
        )
    }

    override suspend fun getStarredRepositories(): List<Repository> {
        delay(2000)

        return listOf(
            Repository("AlexMarchu", "Teacher of the Pacific Design School", "3400", "320", R.drawable.icon_rep1),
            Repository("sxmurxy0", "Minecraft cheats and puzzles", "2750", "450", R.drawable.icon_rep2),
            Repository("mesenev", "developer and teacher from Vladivostok", "9999", "600", R.drawable.icon_rep3),
        )
    }
}
