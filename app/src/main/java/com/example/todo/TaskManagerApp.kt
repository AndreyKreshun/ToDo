package com.example.todo

/*import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.ui.calendar.CalendarViewModel
import com.example.todo.ui.home.HomeViewModel
import com.example.todo.ui.navigation.TaskManagerNavigation

@Composable
fun TaskManagerApp() {
    val application = LocalContext.current.applicationContext as TaskManagerApp
    val taskDao = application.database.taskDao()

    val homeViewModel: HomeViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(taskDao) as T
            }
        }
    )

    val calendarViewModel: CalendarViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CalendarViewModel(taskDao) as T
            }
        }
    )

    MaterialTheme {
        TaskManagerNavigation()
    }
}*/