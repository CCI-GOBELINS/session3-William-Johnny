package com.example.reminderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.reminderapp.ui.theme.ReminderAppTheme
import composables.AddReminderPage
import composables.CompletedPage
import composables.Home
import composables.ReminderDetailScreen

class Reminder(val id: Int, val name: String, val description: String="none", val link: String="none", val date: String, val time: String, completed: Boolean = false){
    var completed by mutableStateOf(completed)
}

object Data {
    var reminders = mutableStateListOf<Reminder>()

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReminderAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController, startDestination = "main",modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("main") {
                            Home(
                                navController = navController,
                                modifier = Modifier.padding(top=30.dp)
                            )
                        }

                        composable(
                            "category/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("id") ?: 0
                            Page(id = id, navController = navController)
                        }

                        composable("add-reminder-page") {
                            AddReminderPage(modifier = Modifier.fillMaxWidth(), navController = navController)
                        }

                        composable(
                            route = "detail/{reminderId}",
                            arguments = listOf(navArgument("reminderId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val reminderId = backStackEntry.arguments?.getInt("reminderId")
                            val reminder = Data.reminders.firstOrNull { it.id == reminderId }

                            if (reminder != null) {
                                ReminderDetailScreen(
                                    reminder = reminder,
                                    navController = navController,
                                    onDelete = { toDelete ->
                                        Data.reminders.remove(toDelete)
                                        navController.navigate("main")  // Go back after delete
                                    }
                                )
                            } else {
                                Text("Reminder not found", modifier = Modifier.padding(16.dp))
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Page(id: Int, modifier: Modifier = Modifier, navController: NavHostController) {
    when (id) {
        1 -> Text(text = "This is Today's reminder page")
        2 -> Text(text = "This is Scheduled's reminder page")
        3 -> CompletedPage(modifier = Modifier.fillMaxWidth(), navController = navController)
        4 -> Text(text = "404: Page Not Found")
    }
}

