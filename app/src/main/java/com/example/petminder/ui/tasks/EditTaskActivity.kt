package com.example.petminder.ui.task

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R
import com.example.petminder.ui.task.dialog.DeleteTaskDialog

class EditTaskActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editDescription: EditText
    private lateinit var editDate: EditText
    private lateinit var editNote: EditText
    private lateinit var spinnerCompletedBy: Spinner

    private lateinit var btnDelete: Button
    private lateinit var btnSave: Button
    private lateinit var btnCancel: TextView
    private lateinit var btnSkip: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        editName = findViewById(R.id.editName)
        editDescription = findViewById(R.id.editDescription)
        editDate = findViewById(R.id.editDate)
        editNote = findViewById(R.id.editNote)
        spinnerCompletedBy = findViewById(R.id.spinnerCompletedBy)

        btnDelete = findViewById(R.id.btnDelete)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
        btnSkip = findViewById(R.id.btnSkip)
    }

    private fun setupListeners() {
        btnDelete.setOnClickListener {
            DeleteTaskDialog().show(supportFragmentManager, "deleteDialog")
        }

        btnSave.setOnClickListener {
            // TODO: Save changes
            finish()
        }

        btnCancel.setOnClickListener { finish() }
        btnSkip.setOnClickListener { finish() }
    }
}
