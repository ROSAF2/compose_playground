package op.mobile.app.dev.rosaf2.navigationcompose.ui.robots

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import op.mobile.app.dev.rosaf2.navigationcompose.navigation.Screen

@Composable
fun RobotsScreen(navController: NavController, robotsViewModel: RobotsViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.clickable {
                    // Pop everything up to and including the "Home" destination off
                    // the back stack before navigating to the "Home" destination
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                text = "Members",
                color = MaterialTheme.colors.secondary,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            )
            RobotList(robots = robotsViewModel.robots.value)
        }
    }
}
