package com.example.petminder.ui.calendar

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class CalendarFilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_filter_screen)

        setupFilterExit()
    }

    private fun setupFilterExit() {
        findViewById<ImageView>(R.id.filterIcon).setOnClickListener {
            finish()
        }
    }
}
