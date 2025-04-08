package co.feip.fefu2025

import RepositoryListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import co.feip.fefu2025.data.RepositoryImpl
import co.feip.fefu2025.data.repository.RepositoryCardImpl
import co.feip.fefu2025.presentation.screen_repository.RepositoryListViewModel
import co.feip.fefu2025.domain.use_cases.GetRepositoriesUseCase
import co.feip.fefu2025.domain.use_cases.GetRepositoryCardUseCase
import co.feip.fefu2025.presentation.screen_repository.RepositoryCardScreen
import co.feip.fefu2025.presentation.screen_repository.RepositoryViewModel
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FEFU2025AndroidBaseRepoTheme {
                val repository = RepositoryCardImpl()
                val useCase = GetRepositoryCardUseCase(repository)
                val viewModel = RepositoryViewModel(useCase)

                RepositoryCardScreen(viewModel = viewModel)

//                val repository = RepositotyImpl()
//                val useCase = GetRepositoiesUseCase(repository)
//                val viewModel = RepositoryListViewModel(useCase)

//                RepositoryListScreen(viewModel = viewModel)
            }
        }
    }
}
