package com.example.petminder.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R
import com.example.petminder.ui.calendar.CalendarActivity
import com.example.petminder.ui.pets.PetsActivity
import com.example.petminder.ui.settings.SettingsActivity
import com.example.petminder.ui.task.CreateTaskActivity
import com.example.petminder.ui.task.EditTaskActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeDailyTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_daily_task)

        setupFab()
        setupTaskInteraction()
        setupBottomNav()
    }

    private fun setupTaskInteraction() {
        findViewById<ImageView>(R.id.task1).setOnClickListener {
            startActivity(Intent(this, EditTaskActivity::class.java))
        }
    }

    private fun setupFab() {
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, CreateTaskActivity::class.java))
        }
    }

    private fun setupBottomNav() {
        findViewById<ImageView>(R.id.navHome).setOnClickListener { }
        findViewById<ImageView>(R.id.navCalendar).setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }
        findViewById<ImageView>(R.id.navPets).setOnClickListener {
            startActivity(Intent(this, PetsActivity::class.java))
        }
        findViewById<ImageView>(R.id.navSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
