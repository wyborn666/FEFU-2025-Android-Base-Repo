import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.presentation.screen_repository.RepositoryListViewModel
import co.feip.fefu2025.ui.components.RepositoryCard
import co.feip.fefu2025.ui.components.TopBarWithSearch

@Composable
fun RepositoryListScreen(
    viewModel: RepositoryListViewModel,
    onItemClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onNavigateToStarred: () -> Unit
) {
    val repositories = viewModel.repositories.collectAsState().value
    val starred = viewModel.starred.collectAsState().value
    val isLoadingMain = viewModel.isLoadingMain.collectAsState().value
    val errorMessageMain = viewModel.errorMessageMain.collectAsState().value

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.errorEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            if (!isLoadingMain && errorMessageMain == null) {
                TopBarWithSearch(onSearchClick = onSearchClick)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                isLoadingMain -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                errorMessageMain != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = errorMessageMain)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { viewModel.retryMainLoad() }) {
                                Text("Повторить")
                            }
                        }
                    }
                }
                else -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                    ) {
                        Text(
                            text = "My Stars",
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                                .clickable { onNavigateToStarred() }
                        )

                        LazyRow {
                            items(starred) { repo ->
                                RepositoryCard(
                                    repo,
                                    modifier = Modifier.clickable { onItemClick(repo.username) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "All Projects",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                        )

                        LazyColumn {
                            items(repositories) { repo ->
                                RepositoryCard(
                                    repo,
                                    modifier = Modifier.clickable { onItemClick(repo.username) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


