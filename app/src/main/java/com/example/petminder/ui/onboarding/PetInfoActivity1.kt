package com.example.petminder.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R
import kotlinx.android.synthetic.main.activity_pet_info1.*

class PetInfoActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_info1)

        btnNext.setOnClickListener {
            startActivity(Intent(this, PetInfoActivity2::class.java))
        }

        txtSkip.setOnClickListener {
            startActivity(Intent(this, PetInfoSummaryActivity::class.java))
        }
    }
}
