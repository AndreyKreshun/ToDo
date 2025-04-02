package com.example.todo.ui.addtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.Category
import com.example.todo.data.Priority
import com.example.todo.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddTaskViewModel : ViewModel() {
    // Состояние экрана
    private val _uiState = MutableStateFlow(AddTaskUiState())
    val uiState: StateFlow<AddTaskUiState> = _uiState.asStateFlow()

    // Обработчики событий
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

    // Сброс состояния (например, после сохранения)
    fun resetState() {
        _uiState.value = AddTaskUiState()
    }
}

// Состояние UI для экрана добавления задачи
data class AddTaskUiState(
    val taskName: String = "",
    val description: String = "",
    val dueDate: LocalDate = LocalDate.now(),
    val priority: Priority = Priority.MEDIUM,
    val category: Category = Category.WORK
)