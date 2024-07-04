package com.example.todoapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.data.model.TodoItem
import com.example.todoapplication.data.repository.TodoRepo
import com.example.todoapplication.data.room.TodoDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application):AndroidViewModel(application) {

    private val repository: TodoRepo
    val allTodoItems: LiveData<List<TodoItem>>

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepo(todoDao)
        allTodoItems = repository.allTodoItems
    }
    fun addTodoItem(todoItem: TodoItem) = viewModelScope.launch {
        repository.insert(todoItem)
    }
    fun updateTodoItem(todoItem:TodoItem) = viewModelScope.launch{
        repository.update(todoItem)
    }
    fun isTaskCompleted(todoItem: TodoItem, isCompleted: Boolean) = viewModelScope.launch {
        todoItem.isCompleted = isCompleted
        repository.update(todoItem)
    }
    fun deleteTodoItem(todoItem: TodoItem) = viewModelScope.launch {
        repository.delete(todoItem)
    }


}