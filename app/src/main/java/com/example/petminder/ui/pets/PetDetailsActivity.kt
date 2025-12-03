package com.example.petminder.ui.pets

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R
import com.example.petminder.ui.calendar.CalendarScreenActivity
import com.example.petminder.ui.home.HomeDailyTaskActivity
import com.example.petminder.ui.settings.SettingsActivity

class PetDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_details)

        setupTopBar()
        loadPetData()
        setupBottomNav()
    }

    private fun setupTopBar() {
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private fun loadPetData() {
        val petName = intent.getStringExtra("petName") ?: "Pet"
        val petImage = intent.getIntExtra("petImage", R.drawable.ic_pets)

        val txtTitle = findViewById<TextView>(R.id.titleText)
        val txtName = findViewById<TextView>(R.id.petName)
        val imgPet = findViewById<ImageView>(R.id.petImage)

        txtTitle.text = petName.uppercase()
        txtName.text = petName
        imgPet.setImageResource(petImage)
    }

    private fun setupBottomNav() {

        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeDailyTaskActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.navCalendar).setOnClickListener {
            startActivity(Intent(this, CalendarScreenActivity::class.java))
        }

        // PETS (selected) – do nothing

        findViewById<LinearLayout>(R.id.navSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
