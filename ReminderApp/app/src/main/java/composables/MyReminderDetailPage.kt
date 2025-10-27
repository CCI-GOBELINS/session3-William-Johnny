package composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.reminderapp.Data
import com.example.reminderapp.R
import com.example.reminderapp.Reminder
import kotlin.sequences.ifEmpty
import kotlin.text.ifEmpty

@Composable
fun ReminderDetailScreen(
    reminder: Reminder,
    navController: NavHostController,
    onDelete: (Reminder) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back_arrow_icon),
            contentDescription = "Item Image",
            modifier = Modifier
                .padding(16.dp)
                .size(35.dp)
                .clickable(onClick = { navController.navigate("main") })
        )

        Text(reminder.name, style = MaterialTheme.typography.headlineMedium)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Status:", fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(8.dp))
            Text(
                text = if (reminder.completed) "Completed" else "Pending",
                color = if (reminder.completed) Color(0xFF4CAF50) else Color(0xFFFFA000)
            )
        }

        Text("Description:", fontWeight = FontWeight.Bold)
        Text(reminder.description.ifEmpty { "No description provided." })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp), contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_delete_icon),
                contentDescription = "Delete Reminder",
                modifier = Modifier
                    .padding(16.dp)
                    .size(35.dp)
                    .clickable {
                        onDelete(reminder)
                    }
            )
        }
    }
}

