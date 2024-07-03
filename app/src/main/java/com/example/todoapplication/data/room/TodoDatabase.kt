package com.example.todoapplication.data.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapplication.data.model.TodoDao

abstract class TodoDatabase:RoomDatabase() {

    abstract fun todoDao():TodoDao

    companion object{
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(applicationContext:Context):TodoDatabase{
            if(INSTANCE != null) return INSTANCE as TodoDatabase
            val instance = Room.databaseBuilder(
                applicationContext,
                TodoDatabase::class.java,
                "todo_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
}