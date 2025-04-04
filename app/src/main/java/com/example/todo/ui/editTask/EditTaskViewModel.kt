package com.example.todo.ui.editTask

import androidx.lifecycle.ViewModel
import com.example.todo.data.Category
import com.example.todo.data.Priority
import com.example.todo.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class EditTaskViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EditTaskUiState())
    val uiState: StateFlow<EditTaskUiState> = _uiState.asStateFlow()

    // Метод для инициализации текущей задачи
    fun initializeWithTask(task: Task) {
        _uiState.value = EditTaskUiState(
            taskName = task.name,
            description = task.description ?: "",
            dueDate = task.dueDate,
            priority = task.priority,
            category = task.category
        )
    }

    fun onTaskNameChange(newName: String) {
        _uiState.update { it.copy(taskName = newName) }
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.update { it.copy(description = newDescription) }
    }

    fun onDueDateChange(newDate: LocalDate) {
        _uiState.update { it.copy(dueDate = newDate) }
    }

    fun onPriorityChange(newPriority: Priority) {
        _uiState.update { it.copy(priority = newPriority) }
    }

    fun onCategoryChange(newCategory: Category) {
        _uiState.update { it.copy(category = newCategory) }
    }
}

// Отдельное UI-состояние для редактирования (можно и переиспользовать AddTaskUiState, если хочешь)
data class EditTaskUiState(
    val taskName: String = "",
    val description: String = "",
    val dueDate: LocalDate = LocalDate.now(),
    val priority: Priority = Priority.MEDIUM,
    val category: Category = Category.WORK
)
