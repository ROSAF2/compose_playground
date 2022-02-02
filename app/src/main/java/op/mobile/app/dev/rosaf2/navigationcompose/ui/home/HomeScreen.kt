package op.mobile.app.dev.rosaf2.navigationcompose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import op.mobile.app.dev.rosaf2.navigationcompose.R
import op.mobile.app.dev.rosaf2.navigationcompose.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RobotCompanyCard()
            RobotCompanyText(navController)
            Container()
        }
    }
}

@Composable
fun RobotCompanyCard() {
    Card(
        elevation = 10.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Image(
            painterResource(id = R.drawable.robotcompany),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun RobotCompanyText(navController: NavController) {
    Text(
        modifier = Modifier
            .padding(20.dp)
            .clickable {
                navController.navigate(route = Screen.Robots.route)
            },
        text = "Robot Company Inc.",
        color = MaterialTheme.colors.primary,
        fontSize = MaterialTheme.typography.h5.fontSize,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Container() {
    var counter by remember {
        mutableStateOf(0)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Counter(
            counter = counter,
            updateCounter = { newCounter: Int ->
                counter = newCounter
            }
        )
        if (counter > 5)
            Text(
                text = "Clicked more than five times!",
                modifier = Modifier.padding(10.dp)
            )
    }
}

@Composable
fun Counter(counter: Int, updateCounter: (Int) -> Unit) {
    Button(onClick = { updateCounter(counter + 1) }) {
        Text("$counter times clicked.")
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
