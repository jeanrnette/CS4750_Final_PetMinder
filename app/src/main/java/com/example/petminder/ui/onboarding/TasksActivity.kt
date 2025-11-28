package com.example.petminder.ui.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petminder.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TasksActivity : AppCompatActivity() {

    private lateinit var recyclerTasks: RecyclerView
    private lateinit var txtEmptyState: TextView
    private lateinit var btnAddTask: FloatingActionButton
    private val taskList = mutableListOf<Pair<String, String>>() // title + details
    private val adapter by lazy { TasksAdapter(taskList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        recyclerTasks = findViewById(R.id.recyclerTasks)
        txtEmptyState = findViewById(R.id.txtEmptyState)
        btnAddTask = findViewById(R.id.btnAddTask)

        recyclerTasks.layoutManager = LinearLayoutManager(this)
        recyclerTasks.adapter = adapter

        updateEmptyState()

        btnAddTask.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            createTaskLauncher.launch(intent)
        }
    }

    private val createTaskLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val title = data?.getStringExtra("task_title") ?: return@registerForActivityResult
            val details = data.getStringExtra("task_details") ?: ""
            taskList.add(title to details)
            adapter.notifyItemInserted(taskList.size - 1)
            updateEmptyState()
        }
    }

    private fun updateEmptyState() {
        txtEmptyState.visibility = if (taskList.isEmpty()) View.VISIBLE else View.GONE
    }
}
