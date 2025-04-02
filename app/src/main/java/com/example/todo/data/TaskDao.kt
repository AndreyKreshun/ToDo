package com.example.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface TaskDao {
    suspend fun insert(task: Task)

    suspend fun update(task: Task)

    suspend fun delete(task: Task)

    suspend fun getTaskById(taskId: Int): Task?

    fun getAllTasks(): Flow<List<Task>>
}