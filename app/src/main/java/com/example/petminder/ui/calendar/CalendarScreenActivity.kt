package com.example.petminder.ui.calendar

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R
import com.example.petminder.ui.home.HomeDailyTaskActivity
import com.example.petminder.ui.home.HomeNoTaskActivity
import com.example.petminder.ui.pets.PetsActivity
import com.example.petminder.ui.settings.SettingsActivity

class CalendarScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_screen)

        setupFilterButton()
        setupAddButton()
        setupBottomNavigation()
    }

    private fun setupFilterButton() {
        findViewById<ImageView>(R.id.filterIcon).setOnClickListener {
            startActivity(Intent(this, CalendarFilterActivity::class.java))
        }
    }

    private fun setupAddButton() {
        findViewById<ImageView>(R.id.addButton).setOnClickListener {
            startActivity(Intent(this, HomeDailyTaskActivity::class.java))
        }
    }

    private fun setupBottomNavigation() {
        findViewById<ImageView>(R.id.navHome)?.setOnClickListener {
            startActivity(Intent(this, HomeNoTaskActivity::class.java))
        }
        // Already on Calendar screen
        findViewById<ImageView>(R.id.navPets)?.setOnClickListener {
            startActivity(Intent(this, PetsActivity::class.java))
        }
        findViewById<ImageView>(R.id.navSettings)?.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
