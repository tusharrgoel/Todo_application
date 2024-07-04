package com.example.todoapplication.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(todoItem: TodoItem)

    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<TodoItem>>

    @Delete
    fun delete(todoItem: TodoItem)

    @Update
    fun update(todoItem: TodoItem)

    @Query("SELECT * FROM todos WHERE id = :taskId")
     fun getTaskById(taskId: Int): TodoItem?
}
