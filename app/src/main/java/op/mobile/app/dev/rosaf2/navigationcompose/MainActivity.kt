package op.mobile.app.dev.rosaf2.navigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import op.mobile.app.dev.rosaf2.navigationcompose.navigation.SetUpNavGraph
import op.mobile.app.dev.rosaf2.navigationcompose.ui.theme.NavigationComposeTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavigationComposeTheme {
                navController = rememberNavController()
                SetUpNavGraph(navController = navController)
            }
        }
    }
}
