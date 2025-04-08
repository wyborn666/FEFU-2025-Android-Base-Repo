package co.feip.fefu2025.data.repository

import co.feip.fefu2025.domain.entities.Language
import co.feip.fefu2025.domain.entities.RepositoryCard
import co.feip.fefu2025.domain.repository.RepositoryCardRepository
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random

class RepositoryCardImpl : RepositoryCardRepository {

    override fun getRepositoryCard(): RepositoryCard {
        val languages = generateLanguages()
        return RepositoryCard(
            username = "wyborn666",
            description = "This is my repository for android",
            languages = languages,
            createdDate = getFormattedDate(),
            stars = "3.2K",
            forks = "64"
        )
    }

    private fun generateLanguages(): List<Language> {
        val langNames = listOf("Kotlin", "Java", "Python", "C++")
        val percentages = List(langNames.size) { Random.nextInt(1, 101).toFloat() }
        val total = percentages.sum()
        return langNames.mapIndexed { index, name ->
            val percentage = (percentages[index] / total * 100 * 10).roundToInt() / 10f
            Language(name, percentage, getRandomColor())
        }
    }

    private fun getFormattedDate(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun getRandomColor(): Int {
        val random = Random.Default
        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)
        return android.graphics.Color.rgb(red, green, blue)
    }
}
