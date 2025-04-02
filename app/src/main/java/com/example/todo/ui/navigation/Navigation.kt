package com.example.todo.ui.navigation

/*import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.ui.home.HomeScreen

@Composable
fun TaskManagerNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onAddTask = { navController.navigate("add") },
                onTaskClick = { taskId -> navController.navigate("edit/$taskId") },
                onNavigateToCalendar = { navController.navigate("calendar") },
                onNavigateToSettings = { navController.navigate("settings") }
            )
        }
        composable("add") {
            AddEditTaskScreen(onBack = { navController.popBackStack() })
        }
        composable("edit/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            AddEditTaskScreen(
                taskId = taskId,
                onBack = { navController.popBackStack() }
            )
        }
        composable("calendar") {
            CalendarScreen(
                onTaskClick = { taskId -> navController.navigate("edit/$taskId") },
                onBack = { navController.popBackStack() }
            )
        }
        composable("settings") {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}*/