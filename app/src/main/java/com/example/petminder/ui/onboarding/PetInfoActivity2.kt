package com.example.petminder.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R
import kotlinx.android.synthetic.main.activity_pet_info2.*

class PetInfoActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_info2)

        btnNext2.setOnClickListener {
            startActivity(Intent(this, PetInfoSummaryActivity::class.java))
        }
    }
}
