package op.mobile.app.dev.rosaf2.navigationcompose.ui.robots

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import op.mobile.app.dev.rosaf2.navigationcompose.R
import op.mobile.app.dev.rosaf2.navigationcompose.model.Robot

@Composable
fun RobotItem(robot: Robot) {
    var isSelected by rememberSaveable {
        mutableStateOf(false)
    }

    val targetColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colors.secondary else Color.Transparent,
        animationSpec = tween(300)
    )

    Card(
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(20.dp, 10.dp)
            .clickable { isSelected = !isSelected },
        shape = RoundedCornerShape(10.dp)
    ) {
        Surface(color = targetColor) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = robot.img,
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.placeholder)
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
                Column {
                    Text(
                        text = robot.code,
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = robot.position,
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}
