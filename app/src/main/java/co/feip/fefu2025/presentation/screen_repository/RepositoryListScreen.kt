
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import co.feip.fefu2025.presentation.screen_repository.RepositoryListViewModel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.ui.components.RepositoryCard
import co.feip.fefu2025.ui.components.TopBarWithSearch

@Composable
fun RepositoryListScreen(viewModel: RepositoryListViewModel) {
    val repositories by viewModel.repositories.collectAsState()
    val starred by viewModel.starred.collectAsState()

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
                    items(starred) { repo ->
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

