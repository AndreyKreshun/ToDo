package com.example.todo.ui.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todo.data.Task
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope

class MainViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks: SnapshotStateList<Task> get() = _tasks

    fun addTask(task: Task) {
        val newTask = task.copy(id = generateId())
        _tasks.add(newTask)
    }

    fun updateTask(updated: Task) {
        val index = _tasks.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            _tasks[index] = updated
        }
    }

    fun deleteTask(task: Task) {
        _tasks.remove(task)
    }


    private fun generateId(): Int = (_tasks.maxOfOrNull { it.id } ?: 0) + 1
}
