package com.example.todoapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapplication.data.model.TodoDao
import com.example.todoapplication.data.model.TodoItem
import java.util.concurrent.atomic.AtomicLong

class TodoRepo(private val todoDao: TodoDao) {
    val allTodoItems: LiveData<List<TodoItem>> = todoDao.getAllTodos()
    var nextId = AtomicLong(0)
    init {
        generateMockData()
    }

    fun generateMockData() {
        var number = 0;

        repeat(5) {
            var todo = TodoItem(id=nextId.toString(), taskTitle ="Task $number+1" )
            todoDao.insert(todo)
            }

        }
     fun insert(todoItem: TodoItem) {
        todoDao.insert(todoItem)
    }

     fun delete(todoItem: TodoItem) {
        todoDao.delete(todoItem)
    }
}
