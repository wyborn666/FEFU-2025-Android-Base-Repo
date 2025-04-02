package co.feip.fefu2025


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Repository(
    val username: String,
    val description: String,
    val stars: String,
    val forks: String,
    val iconResId: Int
)



@Composable
fun MainScreenRepository() {
    val repositories = listOf(
        Repository("wyborn666", "My repository", "120", "32", R.drawable.me),
        Repository("AlexMarchu", "Teacher of the Pacific Design School", "3400", "320", R.drawable.icon_rep1),
        Repository("sxmurxy0", "Minecraft cheats and puzzles", "2750", "450", R.drawable.icon_rep2),
        Repository("mesenev", "developer and teacher from Vladivostok", "9999", "600", R.drawable.icon_rep3),
    )

    val myStars = listOf(
        Repository("AlexMarchu", "Teacher of the Pacific Design School", "3400", "320", R.drawable.icon_rep1),
        Repository("sxmurxy0", "Minecraft cheats and puzzles", "2750", "450", R.drawable.icon_rep2),
        Repository("mesenev", "developer and teacher from Vladivostok", "9999", "600", R.drawable.icon_rep3),
    )

    Column {
        TopBarWithSearch()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            item {
                Text(
                    text = "My Stars",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                )

                LazyRow {
                    items(myStars) { repo ->
                        RepositoryCard(repo)
                    }
                }
            }
            item {
                Text(
                    text = "All Projects",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
            }

            items(repositories) { repo ->
                RepositoryCard(repo)
            }
        }
    }
}


@Composable
fun RepositoryCard(repository: Repository) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(12.dp),
        shape = RoundedCornerShape(8.dp),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(repository.iconResId),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 3.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = repository.username,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = repository.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Stars",
                        modifier = Modifier.size(22.dp).padding(bottom = 3.dp)
                    )
                    Text(text = repository.stars, fontSize = 15.sp)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.fork),
                        contentDescription = "Forks",
                        modifier = Modifier.size(22.dp)
                    )
                    Text(text = repository.forks, fontSize = 15.sp)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithSearch() {
    var searchQuery by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }

    SearchBar(
        query = searchQuery,
        onQueryChange = { searchQuery = it },
        onSearch = {},
        active = false,
        onActiveChange = { isActive = it },
        placeholder = { Text("Поиск") },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {}

    Spacer(modifier = Modifier.height(16.dp))
}


