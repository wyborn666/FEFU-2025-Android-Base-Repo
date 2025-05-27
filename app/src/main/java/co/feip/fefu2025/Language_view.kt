package co.feip.fefu2025

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.View
import android.widget.TextView

class LanguageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val viewColorCircle: View
    private val languageName: TextView
    private val percentage: TextView

    init {
        inflate(context, R.layout.layout_language_view, this)
        viewColorCircle = findViewById(R.id.viewColorCircle)
        languageName = findViewById(R.id.tv_languageName)
        percentage = findViewById(R.id.percentage)

    }
    fun setLanguageName(name: String) {
        languageName.text = name
    }
    @SuppressLint("SetTextI18n")
    fun setPercentage(value: Float) {
        percentage.text = "${value}%"
    }
    fun setCircleColor(color: Int) {
        viewColorCircle.background.setTint(color)
    }
}