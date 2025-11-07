package com.example.petminder.feature.viewmodel

import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    fun getTaskTitle(taskId: String?): String = taskId ?: ""
    fun updateTaskTitle(taskId: String?, title: String) { /* no-op for now */ }
    fun saveTask(taskId: String?) { /* no-op for now */ }
}
