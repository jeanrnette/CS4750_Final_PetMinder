package com.example.petminder.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class PetInfoActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_info1)

        val nextBtn = findViewById<Button>(R.id.btnNext)
        val skipTxt = findViewById<TextView>(R.id.txtSkip)

        nextBtn.setOnClickListener {
            startActivity(Intent(this, PetInfoActivity2::class.java))
        }

        skipTxt.setOnClickListener {
            startActivity(Intent(this, PetInfoSummaryActivity::class.java))
        }
    }
}
