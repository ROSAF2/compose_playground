package op.mobile.app.dev.rosaf2.navigationcompose.ui.robots

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import op.mobile.app.dev.rosaf2.navigationcompose.model.Robot

@Composable
fun RobotList(robots: List<Robot>) {
    LazyColumn {
        items(robots) {
            RobotItem(robot = it)
        }
    }
}
