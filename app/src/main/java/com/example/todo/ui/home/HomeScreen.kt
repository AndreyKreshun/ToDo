package com.example.todo.ui.home

/*import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.data.Task

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit,
    onAddTask: () -> Unit,
    onTaskCheckedChange: (Task, Boolean) -> Unit
) {
    Scaffold(
        topBar = { AppBar() },
        floatingActionButton = { AddTaskButton(onAddTask) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onCheckedChange = { checked -> onTaskCheckedChange(task, checked) },
                    onClick = { onTaskClick(task) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    TopAppBar(
        title = { Text("Планировщик задач") },
        actions = {
            IconButton(onClick = { /* TODO: Открыть фильтры/сортировку */ }) {
                Icon(Icons.Default.FilterList, contentDescription = "Фильтры")
            }
        }
    )
}

@Composable
private fun AddTaskButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Default.Add, contentDescription = "Добавить задачу")
    }
}*/