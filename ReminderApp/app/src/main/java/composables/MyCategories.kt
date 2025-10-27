package composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ReminderCategories(modifier: Modifier = Modifier, buttonRows: List<String>,  navController: NavController,) {
    val equation = remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        buttonRows.forEach { label ->
            Button(
                label = label,
                onClick = {
                    when (label) {
                        "Today" -> navController.navigate("category/1")
                        "Scheduled" -> navController.navigate("category/2")
                        "Completed" -> navController.navigate("category/3")
                        else -> navController.navigate("category/4")
                    }
                },
                modifier = Modifier
                    .height(50.dp)
            )
        }
    }
}