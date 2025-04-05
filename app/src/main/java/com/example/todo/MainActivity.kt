package com.example.todo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import com.example.todo.data.Category
import com.example.todo.data.Priority
import com.example.todo.data.Task
import com.example.todo.ui.calendar.CalendarScreen
import com.example.todo.ui.navigation.MainScreen
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}





