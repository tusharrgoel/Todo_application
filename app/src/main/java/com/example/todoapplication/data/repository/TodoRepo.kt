package com.example.todoapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapplication.data.model.TodoDao
import com.example.todoapplication.data.model.TodoItem
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.atomic.AtomicLong

class TodoRepo(private val todoDao: TodoDao) {
    val allTodoItems: LiveData<List<TodoItem>> = todoDao.getAllTodos()
    var nextId = AtomicLong(0)
    private val firebaseDatabase = FirebaseDatabase.getInstance().getReference("todoItems")
    init {
        generateMockData()
    }

    private fun generateMockData() {
        var number = 0;

        repeat(5) {
            var todo = TodoItem(id =nextId.toString(), taskTitle ="Task $number+1" )
            todoDao.insert(todo)
            }

        }
     fun insert(todoItem: TodoItem) {
        todoDao.insert(todoItem)
         syncWithFirebase()
    }

     fun delete(todoItem: TodoItem) {
        todoDao.delete(todoItem)
         syncWithFirebase()
    }
    fun update(todoItem: TodoItem){
        todoDao.update(todoItem)
        syncWithFirebase()
    }

    fun getTodoById(taskId:Int){
        todoDao.getTaskById(taskId)
    }

    private fun syncWithFirebase() {
        firebaseDatabase.setValue(allTodoItems.value)
    }
}
