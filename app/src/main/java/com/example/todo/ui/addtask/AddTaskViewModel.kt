package com.example.todo.ui.addtask

import androidx.lifecycle.ViewModel
import com.example.todo.data.Category
import com.example.todo.data.Priority
import com.example.todo.data.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.util.UUID

class AddTaskViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AddTaskUiState())
    val uiState: StateFlow<AddTaskUiState> = _uiState.asStateFlow()

    private val db = FirebaseFirestore.getInstance()

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

    fun resetState() {
        _uiState.value = AddTaskUiState()
    }

    fun saveTaskToFirebase(task: Task, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        val taskMap = mapOf(
            "name" to task.name,
            "description" to task.description,
            "dueDate" to task.dueDate.toString(),
            "priority" to task.priority.name,
            "category" to task.category.name
        )

        val taskId = UUID.randomUUID().toString()
        db.collection("tasks")
            .document(taskId)
            .set(taskMap)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onError(exception) }
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