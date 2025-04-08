package co.feip.fefu2025

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.ui.draw.clip


@Composable
fun LanguageDistributionBar(languages: List<Pair<String, Float>>, colors: List<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(12.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFFD3D3D3))
    ) {
        languages.forEachIndexed { index, (_, percentage) ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(percentage)
                    .background(Color(colors[index]))
            )
        }
    }
}