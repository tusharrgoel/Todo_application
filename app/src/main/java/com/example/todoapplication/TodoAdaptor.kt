package com.example.todoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.data.model.TodoItem


class TodoAdapter(private var todos: List<TodoItem>,private val onEditClick: (TodoItem) -> Unit,private val onTaskStatusChanged: (TodoItem,Boolean) -> Unit ,private val onDeleteClick: (TodoItem) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(item:View):RecyclerView.ViewHolder(item){
        private var todoName:TextView = item.findViewById(R.id.todoname)
        private var btnDelete:Button = item.findViewById(R.id.btnDelete)
        private var btnEdit:Button = item.findViewById(R.id.btnEdit)
        private var isCompleted: CheckBox = item.findViewById(R.id.checkBox)

        fun bind(todoItem: TodoItem) {
            todoName.text = todoItem.taskTitle
            isCompleted.isChecked = todoItem.isCompleted
            btnDelete.setOnClickListener {
                onDeleteClick(todoItem)
            }
            isCompleted.setOnCheckedChangeListener { _, isChecked ->
                todoItem.isCompleted = isChecked
                onTaskStatusChanged(todoItem,isChecked)
            }
            btnEdit.setOnClickListener{
                onEditClick(todoItem)
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
    fun updateTodos(newTodos: List<TodoItem>) {
        todos = newTodos
        notifyDataSetChanged()
    }

}

