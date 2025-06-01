package co.feip.fefu2025.navigation

import RepositoryListScreen
import StarredRepositoriesScreen
import  androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import co.feip.fefu2025.presentation.screen_repository.RepositoryCardScreen
import co.feip.fefu2025.presentation.screen_repository.RepositoryListViewModel
import co.feip.fefu2025.presentation.screen_repository.RepositoryViewModel


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
                onNavigateToStarred = {
                    navController.navigate("starred")
                }
            )
        }

        composable(
            route = "detail/{repoId}",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "mysuperapp://repo/{repoId}"
                }
            )
        ) { backStackEntry ->
            val repoId = backStackEntry.arguments?.getString("repoId") ?: return@composable
            val viewModel = cardViewModelFactory(repoId)
            RepositoryCardScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("starred") {
            StarredRepositoriesScreen(
                viewModel = listViewModel,
                onBackClick = { navController.popBackStack() },
                onItemClick = {repoId ->
                    navController.navigate("detail/$repoId")
                }
            )
        }
    }
}