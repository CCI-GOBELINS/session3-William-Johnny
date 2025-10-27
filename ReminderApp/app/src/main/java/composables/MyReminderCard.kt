package composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.reminderapp.Data
import com.example.reminderapp.R
import com.example.reminderapp.Reminder

@Composable
fun ReminderCard(reminder: Reminder, modifier: Modifier = Modifier, selected: Boolean = false, onDelete: (Reminder) -> Unit, navController: NavHostController) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(selected) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                reminder.completed = !selected
            }
            .padding(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier
            .padding(start = 10.dp)
            .selectableGroup()
            .selectable(
                selected = selectedOption,
                onClick = {
                    onOptionSelected(!selected)
                    reminder.completed = !selected },
                role = Role.RadioButton
            )) {
            RadioButton(
                selected = selectedOption,
                onClick = null
            )
            Text(
                text = reminder.name, modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_forward_icon),
                contentDescription = "Go to detail",
                modifier = Modifier
                    .padding(16.dp)
                    .size(35.dp)
                    .clickable { navController.navigate("detail/${reminder.id}") }
            )
        }

    }
}