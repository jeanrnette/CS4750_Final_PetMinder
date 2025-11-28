package com.example.petminder.ui.onboarding

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class PetInfoActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_info2)

        val btnNext2 = findViewById<Button>(R.id.btnNext2)
        btnNext2.setOnClickListener {
            finish()
        }
    }
}
