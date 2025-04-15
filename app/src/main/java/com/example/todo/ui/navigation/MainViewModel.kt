package com.example.todo.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todo.data.Task
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.example.todo.data.Category
import com.example.todo.data.Priority
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate

class MainViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> get() = _tasks

    private val db = FirebaseFirestore.getInstance()

    init {
        loadTasksFromFirebase()
    }

    @SuppressLint("NewApi")
    private fun loadTasksFromFirebase() {
        db.collection("tasks")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("Firebase", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val taskList = snapshot.documents.mapNotNull { doc ->
                        try {
                            val id = doc.id.hashCode() // Можно использовать doc.id как строку
                            val name = doc.getString("name") ?: return@mapNotNull null
                            val description = doc.getString("description") ?: ""
                            val dueDate = LocalDate.parse(doc.getString("dueDate"))
                            val priority = Priority.valueOf(doc.getString("priority") ?: Priority.MEDIUM.name)
                            val category = Category.valueOf(doc.getString("category") ?: Category.WORK.name)
                            Task(id, name, description, dueDate, priority, category)
                        } catch (ex: Exception) {
                            Log.e("Firebase", "Ошибка преобразования задачи: ${ex.message}")
                            null
                        }
                    }
                    _tasks.clear()
                    _tasks.addAll(taskList)
                }
            }
    }

    fun deleteTask(task: Task) {
        db.collection("tasks")
            .get()
            .addOnSuccessListener { snapshot ->
                val doc = snapshot.documents.find { it.getString("name") == task.name }
                doc?.reference?.delete()
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Ошибка при удалении: ${e.message}")
            }
    }

    fun addTask(task: Task) {
        // локально добавлять не нужно — слушатель сам добавит из Firebase
    }

    fun updateTask(updated: Task) {
        db.collection("tasks")
            .get()
            .addOnSuccessListener { snapshot ->
                val doc = snapshot.documents.find { it.getString("name") == updated.name }
                doc?.reference?.set(
                    mapOf(
                        "name" to updated.name,
                        "description" to updated.description,
                        "dueDate" to updated.dueDate.toString(),
                        "priority" to updated.priority.name,
                        "category" to updated.category.name
                    )
                )
            }
    }
}

