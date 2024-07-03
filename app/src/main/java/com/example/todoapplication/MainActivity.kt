package com.example.todoapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.data.model.TodoItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicLong


class MainActivity : AppCompatActivity() {

        private lateinit var userName: TextView
        private lateinit var todoView: RecyclerView
        private lateinit var addTaskButton: Button
        private lateinit var addTask: EditText
        private lateinit var todoAdapter: TodoAdapter
        private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        userName = findViewById(R.id.textView2)
        todoView = findViewById(R.id.recyclerView)
        addTaskButton = findViewById(R.id.addtaskbutton)
        addTask = findViewById(R.id.addtasktext)

        userName.text = getString(R.string.loading_text)

        todoAdapter = TodoAdapter(mutableListOf()) { todoItem ->
            viewModel.deleteTodoItem(todoItem)
        }

        fetchUserName()


        val layoutManager = GridLayoutManager(this, 2)
        todoView.layoutManager = layoutManager
        todoView.adapter = todoAdapter

        AtomicLong(0)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        addTaskButton.setOnClickListener {
            val taskTitle = addTask.text.toString()
            if (taskTitle.isNotEmpty()) {
                var tid = AtomicLong(0)
               viewModel.addTodoItem(TodoItem(id = tid.toString(),taskTitle = taskTitle))
                addTask.text.clear()
            }
        }
    }
    private fun fetchUserName(){
        val retrofit = Retrofit.Builder().baseUrl("https://api.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getName()
        call.enqueue(object : Callback<ApiService.ServerResponse> {
            override fun onResponse(
                call: Call<ApiService.ServerResponse>,
                response: Response<ApiService.ServerResponse>
            ) {
                if (response.isSuccessful) {
                    val serverText = response.body()?.text ?: "Loading"
                    userName.text = serverText
                }
            }

            override fun onFailure(call: Call<ApiService.ServerResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
