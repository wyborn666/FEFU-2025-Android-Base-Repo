package co.feip.fefu2025.data.repository

import co.feip.fefu2025.R
import co.feip.fefu2025.domain.entities.Language
import co.feip.fefu2025.domain.entities.RepositoryCard
import co.feip.fefu2025.domain.repository.RepositoryCardRepository
import java.text.SimpleDateFormat
import java.util.*

class RepositoryCardImpl(private val username: String) : RepositoryCardRepository {

    override fun getRepositoryCard(): RepositoryCard {
        return when (username) {
            "wyborn666" -> RepositoryCard(
                username = "wyborn666",
                description = "This is my repository for Android",
                languages = listOf(
                    Language("Python", 60f, android.graphics.Color.argb(180, 255, 0, 0)),
                    Language("Kotlin", 5f, android.graphics.Color.argb(255, 66, 170, 255)),
                    Language("C++", 35f, android.graphics.Color.argb(255, 255, 255, 0))
                ),
                createdDate = getFormattedDate(),
                stars = "120",
                forks = "32",
                iconResId = R.drawable.me
            )
            "AlexMarchu" -> RepositoryCard(
                username = "AlexMarchu",
                description = "Creative teacher of Pacific Design School",
                languages = listOf(
                    Language("Python", 70f, android.graphics.Color.argb(255, 151, 154, 170)),
                    Language("TypeScript", 30f, android.graphics.Color.argb(255, 0, 0, 0))
                ),
                createdDate = getFormattedDate(),
                stars = "3400",
                forks = "320",
                iconResId = R.drawable.icon_rep1
            )
            "sxmurxy0" -> RepositoryCard(
                username = "sxmurxy0",
                description = "Did you buy a horsepower shampoo?",
                languages = listOf(
                    Language("Java", 70f, android.graphics.Color.argb(255, 165, 0, 255)),
                    Language("C++", 15f, android.graphics.Color.argb(255, 255, 165, 0)),
                    Language("Python", 15f, android.graphics.Color.argb(255, 0, 128, 0))

                ),
                createdDate = getFormattedDate(),
                stars = "2750",
                forks = "450",
                iconResId = R.drawable.icon_rep2

            )
            "mesenev" -> RepositoryCard(
                username = "mesenev",
                description = "I really like reflection and aesthetics.",
                languages = listOf(
                    Language("C++", 5f, android.graphics.Color.argb(255, 100, 28, 52)),
                    Language("Python", 95f, android.graphics.Color.argb(255, 216, 112, 147))

                ),
                createdDate = getFormattedDate(),
                stars = "9999",
                forks = "600",
                iconResId = R.drawable.icon_rep3
            )

            else -> RepositoryCard(
                username = username,
                description = "Default repo description",
                languages = listOf(
                    Language("C++", 100f, android.graphics.Color.RED)
                ),
                createdDate = getFormattedDate(),
                stars = "0",
                forks = "0",
                iconResId = R.drawable.android
            )
        }
    }

    private fun getFormattedDate(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

}
