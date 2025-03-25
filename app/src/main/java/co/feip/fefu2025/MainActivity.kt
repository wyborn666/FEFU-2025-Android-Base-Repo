package co.feip.fefu2025

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import java.math.RoundingMode
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FEFU2025AndroidBaseRepoTheme {
                RepositoryCard()
            }
        }
    }
}
