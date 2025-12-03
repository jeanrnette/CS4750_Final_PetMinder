package com.example.petminder.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class PetInfoActivity1 : AppCompatActivity() {

    private lateinit var inputPetName: EditText
    private lateinit var inputSpecies: EditText
    private lateinit var inputSex: EditText
    private lateinit var inputAge: EditText
    private lateinit var btnNext: Button
    private lateinit var txtSkip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_info1)

        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        inputPetName = findViewById(R.id.inputPetName)
        inputSpecies = findViewById(R.id.inputSpecies)
        inputSex = findViewById(R.id.inputSex)
        inputAge = findViewById(R.id.inputAge)
        btnNext = findViewById(R.id.btnNext)
        txtSkip = findViewById(R.id.txtSkip)
    }

    private fun setupListeners() {

        btnNext.setOnClickListener {

            val name = inputPetName.text.toString().trim()
            val species = inputSpecies.text.toString().trim()
            val sex = inputSex.text.toString().trim()
            val age = inputAge.text.toString().trim()

            if (name.isEmpty()) { inputPetName.error = "Required"; return@setOnClickListener }
            if (species.isEmpty()) { inputSpecies.error = "Required"; return@setOnClickListener }
            if (sex.isEmpty()) { inputSex.error = "Required"; return@setOnClickListener }
            if (age.isEmpty()) { inputAge.error = "Required"; return@setOnClickListener }

            // Pass data to PetInfoActivity2
            val intent = Intent(this, PetInfoActivity2::class.java).apply {
                putExtra("petName", name)
                putExtra("species", species)
                putExtra("sex", sex)
                putExtra("age", age)
            }
            startActivity(intent)
            finish()
        }

        // Skip to summary
        txtSkip.setOnClickListener {
            startActivity(Intent(this, PetInfoSummaryActivity::class.java))
            finish()
        }
    }
}
