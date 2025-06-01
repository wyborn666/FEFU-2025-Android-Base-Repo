package co.feip.fefu2025.navigation

import RepositoryListScreen
import StarredRepositoriesScreen
import  androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.feip.fefu2025.data.RepositoryImpl
import co.feip.fefu2025.domain.use_cases.GetRepositoriesUseCase
import co.feip.fefu2025.presentation.screen_repository.RepositoryCardScreen
import co.feip.fefu2025.presentation.screen_repository.RepositoryListViewModel
import co.feip.fefu2025.presentation.screen_repository.RepositorySearchScreen
import co.feip.fefu2025.presentation.screen_repository.RepositorySearchViewModel
import co.feip.fefu2025.presentation.screen_repository.RepositorySearchViewModelFactory
import co.feip.fefu2025.presentation.screen_repository.RepositoryViewModel
import co.feip.fefu2025.presentation.screen_repository.StarredRepositoriesViewModel
import co.feip.fefu2025.presentation.screen_repository.StarredRepositoriesViewModelFactory


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    listViewModel: RepositoryListViewModel,
    cardViewModelFactory: (String) -> RepositoryViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            RepositoryListScreen(
                viewModel = listViewModel,
                onItemClick = { username ->
                    navController.navigate("detail/$username")
                },
                onSearchClick = {
                    navController.navigate("search")
                },
                onNavigateToStarred = { navController.navigate("starred") }
            )
        }

        composable("detail/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: return@composable
            val viewModel = cardViewModelFactory(username)
            RepositoryCardScreen(viewModel = viewModel, onBackClick = { navController.popBackStack() })
        }

        composable("starred") {
            val repository = RepositoryImpl()
            val useCase = GetRepositoriesUseCase(repository)
            val factory = StarredRepositoriesViewModelFactory(useCase)
            val starredViewModel: StarredRepositoriesViewModel = viewModel(factory = factory)
            StarredRepositoriesScreen(
                viewModel = starredViewModel,
                onBackClick = { navController.popBackStack() },
                onItemClick = { username -> navController.navigate("detail/$username") }
            )
        }
        composable("search") {
            val repository = RepositoryImpl()
            val useCase = GetRepositoriesUseCase(repository)
            val factory = RepositorySearchViewModelFactory(useCase)
            val viewModel: RepositorySearchViewModel = viewModel(factory = factory)

            RepositorySearchScreen(viewModel = viewModel, navController = navController)
        }
    }
}