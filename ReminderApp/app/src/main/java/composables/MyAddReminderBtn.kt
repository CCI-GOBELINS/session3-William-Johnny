package composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddReminder(modifier: Modifier = Modifier, navController: NavController,) {
    Button(
        label = "+",
        onClick = {
            navController.navigate("add-reminder-page")
        },
        fontSize = 48.sp
    )
}