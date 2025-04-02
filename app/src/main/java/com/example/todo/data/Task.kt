package com.example.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

data class Task(
    val name: String,
    val description: String,
    val dueDate: LocalDate,
    val priority: Priority,
    val category: Category
)

enum class Priority(val displayName: String) {
    LOW("Низкий"),
    MEDIUM("Средний"),
    HIGH("Высокий")
}

enum class Category(val displayName: String) {
    WORK("Работа"),
    PERSONAL("Личное"),
    SHOPPING("Покупки"),
    OTHER("Другое")
}