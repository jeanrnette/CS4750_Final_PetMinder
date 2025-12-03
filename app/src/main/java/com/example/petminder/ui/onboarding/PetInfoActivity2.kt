package com.example.petminder.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class PetInfoActivity2 : AppCompatActivity() {

    private lateinit var inputBirthday: EditText
    private lateinit var inputWeight: EditText
    private lateinit var inputHeight: EditText
    private lateinit var inputVaccines: EditText
    private lateinit var btnNext2: Button
    private lateinit var txtSkip2: TextView

    private var petName: String = ""
    private var species: String = ""
    private var sex: String = ""
    private var age: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_info2)

        loadPreviousData()
        setupViews()
        setupListeners()
    }

    private fun loadPreviousData() {
        petName = intent.getStringExtra("petName") ?: ""
        species = intent.getStringExtra("species") ?: ""
        sex = intent.getStringExtra("sex") ?: ""
        age = intent.getStringExtra("age") ?: ""
    }

    private fun setupViews() {
        inputBirthday = findViewById(R.id.inputBirthday)
        inputWeight = findViewById(R.id.inputWeight)
        inputHeight = findViewById(R.id.inputHeight)
        inputVaccines = findViewById(R.id.inputVaccines)
        btnNext2 = findViewById(R.id.btnNext2)
        txtSkip2 = findViewById(R.id.txtSkip2)
    }

    private fun setupListeners() {

        btnNext2.setOnClickListener {

            val birthday = inputBirthday.text.toString().trim()
            val weight = inputWeight.text.toString().trim()
            val height = inputHeight.text.toString().trim()
            val vaccines = inputVaccines.text.toString().trim()

            if (birthday.isEmpty()) { inputBirthday.error = "Required"; return@setOnClickListener }
            if (weight.isEmpty()) { inputWeight.error = "Required"; return@setOnClickListener }
            if (height.isEmpty()) { inputHeight.error = "Required"; return@setOnClickListener }
            if (vaccines.isEmpty()) { inputVaccines.error = "Required"; return@setOnClickListener }

            val intent = Intent(this, PetInfoSummaryActivity::class.java).apply {
                putExtra("petName", petName)
                putExtra("species", species)
                putExtra("sex", sex)
                putExtra("age", age)
                putExtra("birthday", birthday)
                putExtra("weight", weight)
                putExtra("height", height)
                putExtra("vaccines", vaccines)
            }
            startActivity(intent)
            finish()
        }

        txtSkip2.setOnClickListener {
            startActivity(Intent(this, PetInfoSummaryActivity::class.java))
            finish()
        }
    }
}
