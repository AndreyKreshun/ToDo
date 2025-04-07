package com.example.todo.ui.navigation

import HomeScreen
import LoginScreen
import SignupScreen
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.ui.addtask.AddTaskScreen
import com.example.todo.ui.calendar.CalendarScreen
import com.example.todo.ui.editTask.EditTaskScreen



@SuppressLint("NewApi")
@Composable
fun MainScreen(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val navController = rememberNavController()
    val tasks = viewModel.tasks

    NavHost(navController = navController, startDestination = NavRoutes.LOGIN) {
        // Authorization screens
        composable(NavRoutes.LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(NavRoutes.SIGNUP) {
            SignupScreen(navController = navController)
        }
        composable(NavRoutes.HOME) {
            HomeScreen(
                tasks = tasks,
                onTaskClick = { task ->
                    navController.navigate("${NavRoutes.EDIT_TASK}/${task.id}")
                },
                onAddTaskClick = {
                    navController.navigate(NavRoutes.ADD_TASK)
                },
                onDeleteTask = { task ->
                    viewModel.deleteTask(task)
                },
                onCalendarClick = {
                    navController.navigate(NavRoutes.CALENDAR) // Переход в календарь
                }
            )
        }

        composable(NavRoutes.ADD_TASK) {
            AddTaskScreen(
                onBackClick = { navController.popBackStack() },
                onSaveClick = { task ->
                    viewModel.addTask(task)
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "${NavRoutes.EDIT_TASK}/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId")
            val taskToEdit = tasks.find { it.id == taskId }

            if (taskToEdit != null) {
                EditTaskScreen(
                    task = taskToEdit,
                    onBackClick = { navController.popBackStack() },
                    onSaveClick = { updatedTask ->
                        viewModel.updateTask(updatedTask)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(NavRoutes.CALENDAR) {
            CalendarScreen(tasks = tasks, navController = navController)
        }
    }
}

