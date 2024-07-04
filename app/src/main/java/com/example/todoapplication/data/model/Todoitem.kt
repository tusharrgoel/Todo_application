package com.example.todoapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todos")
data class TodoItem(
    @PrimaryKey val id: String,
    var taskTitle: String,
    val createdAt: java.util.Date = Date(),
    val deleted:Boolean? = false,
    var isCompleted:Boolean = false
)

