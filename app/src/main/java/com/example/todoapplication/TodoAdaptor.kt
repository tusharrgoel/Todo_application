package com.example.todoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.data.model.TodoItem
import com.example.todoapplication.data.repository.TodoRepo

class TodoAdapter(private var todos: List<TodoItem>,private val onDeleteClick: (TodoItem) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(item:View):RecyclerView.ViewHolder(item){

        private var todoName:TextView = item.findViewById(R.id.todoname)
        private var btnDelete:Button = item.findViewById(R.id.btnDelete)
        fun bind(todoItem: TodoItem) {
            todoName.text = todoItem.taskTitle
            btnDelete.setOnClickListener {
                onDeleteClick(todoItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TodoViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.bind(currentTodo)
    }

}

