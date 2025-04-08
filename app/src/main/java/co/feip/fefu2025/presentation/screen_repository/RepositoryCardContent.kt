import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.entities.RepositoryCard
import co.feip.fefu2025.ui.components.LanguageDistributionBar
import co.feip.fefu2025.ui.components.LanguageView
import co.feip.fefu2025.ui.components.CustomFlexBoxLayout
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun RepositoryCardContent(card: RepositoryCard) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(24.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.android),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp).padding(top = 3.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = card.username,
                    fontFamily = FontFamily.Monospace,
                    color = Color(card.languages.random().color),
                    modifier = Modifier.padding(top = 3.dp),
                )
            }

            Text(
                text = card.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Languages:",
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            val languages = card.languages
            val colors = languages.map { it.color }

            LanguageDistributionBar(languages.map { it.name to it.percentage }, colors)

            Spacer(modifier = Modifier.height(8.dp))

            AndroidView(factory = { context ->
                CustomFlexBoxLayout(context).apply {
                    for (lang in languages) {
                        val langView = LanguageView(context).apply {
                            setLanguageName(lang.name)
                            setCircleColor(lang.color)
                            setPercentage(lang.percentage)
                        }
                        addView(langView)
                    }
                }
            })

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Created on: ${card.createdDate}",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Stars",
                        modifier = Modifier.size(16.dp).padding(top = 4.dp),
                    )
                    Text(
                        text = card.stars,
                        modifier = Modifier.padding(top = 4.dp),
                        fontSize = 12.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.fork),
                        contentDescription = "Forks",
                        modifier = Modifier.size(16.dp).padding(top = 4.dp)
                    )
                    Text(
                        text = card.forks,
                        modifier = Modifier.padding(top = 4.dp),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
