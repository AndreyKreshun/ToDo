package com.example.todo

import HomeScreen
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.data.Category
import com.example.todo.data.Priority
import com.example.todo.data.Task
import com.example.todo.ui.calendar.TaskCalendarScreen
import com.example.todo.ui.theme.ToDoTheme
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sampleTasks = listOf(
            Task("Купить продукты", "Список: молоко, хлеб, яйца", LocalDate.now(), Priority.MEDIUM, Category.SHOPPING),
            Task("Сделать отчёт", "Подготовить данные за месяц", LocalDate.now().plusDays(3), Priority.HIGH, Category.WORK),
            Task("Позвонить другу", "Обсудить планы на выходные", LocalDate.now().plusDays(1), Priority.LOW, Category.PERSONAL)
        )

        setContent {
            MaterialTheme {
                TaskCalendarScreen(tasks = sampleTasks)
            }
        }
    }
}





