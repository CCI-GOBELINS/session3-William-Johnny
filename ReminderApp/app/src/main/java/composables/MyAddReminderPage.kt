package composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reminderapp.Data
import com.example.reminderapp.R
import com.example.reminderapp.Reminder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderPage(modifier: Modifier = Modifier,  navController: NavController) {
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val time = remember { mutableStateOf("") }
    val reminderId = remember { mutableIntStateOf(-1) }
    var showDialExample by remember { mutableStateOf(false) }
    var selectedTime: TimePickerState? by remember { mutableStateOf(null) }
    Column {
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Text(text = "Add reminder", fontSize = 30.sp, textAlign = TextAlign.Center)
            Image(
                painter = painterResource(id = R.drawable.ic_close_icon),
                contentDescription = "Item Image",
                modifier = Modifier
                    .padding(16.dp)
                    .size(35.dp)
                    .clickable(onClick = { navController.navigate("main") })
            )
        }

        OutlinedTextField(
            value = name.value,
            label = { Box(contentAlignment = Alignment.Center, modifier = Modifier.height(25.dp)){Text(text = "Name", fontSize = 20.sp)} },
            shape = RoundedCornerShape(8.dp),
            onValueChange = { name.value = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp))

        OutlinedTextField(
            value = description.value,
            label = { Box(contentAlignment = Alignment.Center, modifier = Modifier.height(25.dp)){Text(text = "Description", fontSize = 20.sp)} },
            shape = RoundedCornerShape(8.dp),
            onValueChange = { description.value = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp))

        DatePickerDocked(label = "Date", modifier = Modifier.padding(30.dp))

        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 30.dp),){
            Text(text ="Add time")
            var checked by remember { mutableStateOf(false) }

            Switch(
                modifier = Modifier.padding(start = 30.dp),
                checked = checked,
                onCheckedChange = {
                    checked = it
                    showDialExample = !showDialExample
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (showDialExample) {
            Box(
                modifier = Modifier.fillMaxWidth() , contentAlignment = Alignment.Center){
                DialUseStateExample(
                    onConfirm = { time ->
                        selectedTime = time
                        showDialExample = false
                    },
                )
            }

        }

        selectedTime?.let {
            time.value = "${it.hour}:${it.minute}"
        }

        Spacer(modifier = Modifier.height(40.dp))

        Box (modifier = Modifier
            .fillMaxWidth()
            , contentAlignment = Alignment.Center){
            Button(
                label = "Done",
                onClick = {
                    reminderId.intValue++
                    Data.reminders.add(Reminder(reminderId.intValue, name.value, date=date.value, time= time.value, description = description.value))
                    navController.navigate("main")
                },
                fontSize = 25.sp
            )
        }
    }

}