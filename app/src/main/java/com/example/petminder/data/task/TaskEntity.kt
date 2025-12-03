package com.example.petminder.data.task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val petId: Int?,
    val title: String,
    val description: String,
    val dateTime: String,
    val importance: String,
    val completedBy: String,
    val notes: String? = null,
    val isRecurring: Boolean = false,
    val repeatType: String? = null
)
