package com.example.todo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.todo.ui.addtask.AddTaskScreen
import com.example.todo.ui.theme.ToDoTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToDoTheme {
                // Оборачиваем в Surface для правильного отображения темы
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddTaskScreen(
                        onBackClick = { finish() }, // Закрываем экран при нажатии "Назад"
                        onSaveClick = { task ->
                            // Обработка сохранения задачи
                            println("Сохранена задача: $task")
                            finish() // Закрываем экран после сохранения
                        }
                    )
                }
            }
        }
    }
}


