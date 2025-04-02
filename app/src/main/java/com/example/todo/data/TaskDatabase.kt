package com.example.todo.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile
        private var Instance: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    TaskDatabase::class.java,
                    "task_database"
                ).build().also { Instance = it }
            }
        }
    }
}