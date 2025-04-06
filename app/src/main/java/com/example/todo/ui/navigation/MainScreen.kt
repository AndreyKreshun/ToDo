package com.example.todo.ui.navigation

import HomeScreen
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.ui.addtask.AddTaskScreen
import com.example.todo.ui.calendar.CalendarScreen
import com.example.todo.ui.editTask.EditTaskScreen
import com.montanainc.simpleloginscreen.screens.LoginScreen
import com.montanainc.simpleloginscreen.screens.SignupScreen


@SuppressLint("NewApi")
@Composable
fun MainScreen(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val navController = rememberNavController()
    val tasks = viewModel.tasks

    NavHost(navController = navController, startDestination = NavRoutes.AUTHORIZATION) {
        // Authorization screens
        composable(NavRoutes.AUTHORIZATION) {
            LoginScreen(navController = navController)
        }
        composable(NavRoutes.REGISTRATION) {
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

