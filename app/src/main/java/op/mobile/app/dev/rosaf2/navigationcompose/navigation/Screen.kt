package op.mobile.app.dev.rosaf2.navigationcompose.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "navigation_home")
    object Robots : Screen(route = "navigation_robots")
}
