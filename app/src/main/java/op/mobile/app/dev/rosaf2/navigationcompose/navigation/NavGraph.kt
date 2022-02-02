package op.mobile.app.dev.rosaf2.navigationcompose.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import op.mobile.app.dev.rosaf2.navigationcompose.ui.home.HomeScreen
import op.mobile.app.dev.rosaf2.navigationcompose.ui.robots.RobotsScreen
import op.mobile.app.dev.rosaf2.navigationcompose.ui.robots.RobotsViewModel

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Robots.route) {
            RobotsScreen(
                navController = navController,
                robotsViewModel = viewModel<RobotsViewModel>()
            )
        }
    }
}
