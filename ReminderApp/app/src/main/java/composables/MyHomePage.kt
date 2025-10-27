package composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.room.util.copy
import com.example.reminderapp.Data
import com.example.reminderapp.Reminder
import kotlin.collections.listOf

@Composable
fun Home(navController: NavHostController,modifier: Modifier = Modifier) {

    Box (modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ReminderCategories(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                buttonRows = listOf("Today", "Scheduled", "Completed"),
                navController = navController
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (Data.reminders.isNotEmpty()) {
                for (el in Data.reminders) {
                    if (!el.completed) {
                        ReminderCard(el, onDelete = { Data.reminders =
                            (Data.reminders - el) as SnapshotStateList<Reminder>
                        },navController = navController)
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth().padding(50.dp), contentAlignment = Alignment.Center
                ) {
                    Text(text = "You don't have any reminders yet")
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp), contentAlignment = Alignment.BottomEnd
            ) {
                AddReminder(navController = navController, modifier = modifier.padding(30.dp))
            }

        }
    }
}