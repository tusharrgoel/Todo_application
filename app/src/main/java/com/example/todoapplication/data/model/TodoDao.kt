package com.example.todoapplication.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(todoItem: TodoItem)

    @Query("SELECT * FROM todo_database")
    fun getAllTodos(): LiveData<List<TodoItem>>

    @Delete
    fun delete(taskId: TodoItem)
}
