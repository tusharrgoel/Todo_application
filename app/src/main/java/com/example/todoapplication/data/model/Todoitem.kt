package com.example.todoapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data class TodoItem(
    @PrimaryKey val id : String,
    val taskTitle: String,
)

