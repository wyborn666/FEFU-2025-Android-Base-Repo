import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.presentation.screen_repository.StarredRepositoriesViewModel
import co.feip.fefu2025.ui.components.RepositoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarredRepositoriesScreen(
    viewModel: StarredRepositoriesViewModel,
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    val starred by viewModel.starred.collectAsState()
    val isLoading by viewModel.isLoadingStars.collectAsState()
    val errorMessage by viewModel.errorMessageStars.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadStarredRepositories()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Starred Repositories") },
                colors = TopAppBarDefaults.topAppBarColors(),
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (errorMessage != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = errorMessage ?: "Unknown Error")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadStarredRepositories() }) {
                        Text("Повторить")
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(8.dp)
            ) {
                items(starred) { repo ->
                    RepositoryCard(
                        repo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onItemClick(repo.username) }
                    )
                }
            }
        }
    }
}
