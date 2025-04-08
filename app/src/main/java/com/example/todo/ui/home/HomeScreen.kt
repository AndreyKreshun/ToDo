import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todo.data.Task
import com.example.todo.ui.home.TaskItem
import com.example.todo.ui.navigation.NavRoutes
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit,
    onAddTaskClick: () -> Unit,
    onDeleteTask: (Task) -> Unit,
    onCalendarClick: () -> Unit,
    onExitClick: () -> Unit
) {
    var taskToDelete by remember { mutableStateOf<Task?>(null) }
    val context = LocalContext.current

    if (taskToDelete != null) {
        AlertDialog(
            onDismissRequest = { taskToDelete = null },
            title = { Text("Удалить задачу") },
            text = { Text("Вы уверены, что хотите удалить задачу \"${taskToDelete?.name}\"?") },
            confirmButton = {
                TextButton(onClick = {
                    onDeleteTask(taskToDelete!!)
                    taskToDelete = null
                }) {
                    Text("Удалить")
                }
            },
            dismissButton = {
                TextButton(onClick = { taskToDelete = null }) {
                    Text("Отмена")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Список задач") },
                actions = {
                    IconButton(onClick = onAddTaskClick) {
                        Icon(Icons.Default.Add, contentDescription = "Добавить задачу")
                    }
                    IconButton(onClick = onCalendarClick) {
                        Icon(Icons.Default.DateRange, contentDescription = "Календарь")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp), // внешний отступ
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onTaskClick = onTaskClick,
                        onTaskLongClick = { taskToDelete = it }
                    )
                }
            }

            // Кнопка выхода
            TextButton(
                onClick = {
                    FirebaseAuth.getInstance().signOut()
                    Toast.makeText(context, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
                    onExitClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Выйти из аккаунта")
            }
        }
    }
}





