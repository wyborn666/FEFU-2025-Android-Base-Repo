package co.feip.fefu2025

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Preview(showBackground = true)
@Composable
fun CardRepository() {
    FEFU2025AndroidBaseRepoTheme {


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
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
                        text = "wyborn666",
                        modifier = Modifier.padding(top = 3.dp),
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black
                    )
                }
                Text(
                    text = "This is my repository for android",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 7.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "Stars",
                            modifier = Modifier.size(16.dp).padding(top = 4.dp),
                        )
                        Text(text = "3.2K",
                            modifier = Modifier.padding(top = 2.dp),
                            fontSize = 12.sp)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 7.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.fork),
                            contentDescription = "Forks",
                            modifier = Modifier.size(16.dp).padding(top = 4.dp)
                        )
                        Text(text = "64",
                            modifier = Modifier.padding(top = 2.dp),
                            fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
