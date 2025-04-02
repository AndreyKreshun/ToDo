package com.example.todo.ui.home

/*import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.Task
import com.example.todo.data.TaskDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(private val taskDao: TaskDao) : ViewModel() {
    val tasks: Flow<List<Task>> = taskDao.getAllTasks()

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.update(task)
        }
    }
}*/