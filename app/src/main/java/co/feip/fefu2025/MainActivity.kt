package co.feip.fefu2025

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import java.math.RoundingMode
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var flexBoxLayout: CustomFlexBoxLayout
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        flexBoxLayout = findViewById(R.id.flexBoxLayout)
        addButton = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            addLanguageView()
        }
    }

    private fun addLanguageView() {
        val randomLanguage = getRandomLanguage()

        val languageView = LanguageView(this)

        val randomColor = Color.argb(
            255,
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )

        val randomPercentage = (Random.nextFloat() * 100).toBigDecimal().setScale(1, RoundingMode.HALF_UP).toFloat()

        languageView.setLanguageName(randomLanguage)
        languageView.setPercentage(randomPercentage)
        languageView.setCircleColor(randomColor)

        flexBoxLayout.addView(languageView)
    }

    private fun getRandomLanguage(): String {
        val languages = Constants.languages
        return languages.random()
    }
}
