package com.example.petminder.ui.task

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class CreateTaskActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editDescription: EditText
    private lateinit var editDate: EditText
    private lateinit var switchRepeat: Switch
    private lateinit var repeatOptions: RadioGroup
    private lateinit var spinnerCompletedBy: Spinner
    private lateinit var spinnerImportance: Spinner

    private lateinit var btnCancel: Button
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        editName = findViewById(R.id.editName)
        editDescription = findViewById(R.id.editDescription)
        editDate = findViewById(R.id.editDate)
        switchRepeat = findViewById(R.id.switchRepeat)
        repeatOptions = findViewById(R.id.repeatOptions)
        spinnerCompletedBy = findViewById(R.id.spinnerCompletedBy)
        spinnerImportance = findViewById(R.id.spinnerImportance)
        btnCancel = findViewById(R.id.btnCancel)
        btnSave = findViewById(R.id.btnSave)
    }

    private fun setupListeners() {

        btnCancel.setOnClickListener { finish() }

        btnSave.setOnClickListener {
            // TODO: Save to DB or ViewModel
            finish()
        }

        switchRepeat.setOnCheckedChangeListener { _, enabled ->
            repeatOptions.isEnabled = enabled
            for (i in 0 until repeatOptions.childCount)
                repeatOptions.getChildAt(i).isEnabled = enabled
        }
    }
}
