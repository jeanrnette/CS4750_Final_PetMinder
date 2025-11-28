package com.example.petminder.ui.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class CreateTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        val edtTaskTitle = findViewById<EditText>(R.id.edtTaskTitle)
        val edtTaskDetails = findViewById<EditText>(R.id.edtTaskDetails)
        val btnSaveTask = findViewById<Button>(R.id.btnSaveTask)

        btnSaveTask.setOnClickListener {
            val title = edtTaskTitle.text.toString().trim()
            val details = edtTaskDetails.text.toString().trim()

            if (title.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("task_title", title)
                resultIntent.putExtra("task_details", details)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                edtTaskTitle.error = "Please enter a title"
            }
        }
    }
}

