package composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.reminderapp.Data
import com.example.reminderapp.R
import com.example.reminderapp.Reminder

@Composable
fun CompletedPage(navController: NavHostController,modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back_arrow_icon),
            contentDescription = "Item Image",
            modifier = Modifier
                .padding(16.dp)
                .size(35.dp)
                .clickable(onClick = { navController.navigate("main") })
        )
        for (el in Data.reminders){
            if (el.completed){
                ReminderCard(el, selected = true, onDelete = { Data.reminders =
                    (Data.reminders - el) as SnapshotStateList<Reminder>
                },navController=navController)
            }
        }
    }
}