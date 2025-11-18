package com.example.petminder.ui.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R
import kotlinx.android.synthetic.main.activity_pet_info_summary.*

class PetInfoSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_info_summary)

        btnFinish.setOnClickListener {
            finish() // Finish onboarding
        }
    }
}
