package co.feip.fefu2025

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import java.math.RoundingMode
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val flexBoxLayout: CustomFlexBoxLayout by lazy {
        findViewById(R.id.flexBoxLayout)
    }
    private val addButton: Button by lazy {
        findViewById(R.id.addButton)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        addButton.setOnClickListener {
            addLanguageView()
        }
    }

    private fun addLanguageView() {
        val randomLanguage = getRandomLanguage()
        val randomColor = Color.argb(
            255,
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        val randomPercentage = (Random.nextFloat() * 100)
            .toBigDecimal()
            .setScale(1, RoundingMode.HALF_UP)
            .toFloat()

        flexBoxLayout.addView(
            LanguageView(this).apply {
                setLanguageName(randomLanguage)
                setPercentage(randomPercentage)
                setCircleColor(randomColor)
            }
        )
    }


    private fun getRandomLanguage(): String {
        val languages = Constants.languages
        return languages.random()
    }
}
