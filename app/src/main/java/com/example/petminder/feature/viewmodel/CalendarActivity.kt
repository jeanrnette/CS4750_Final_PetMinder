package com.example.petminder.feature.calendar

import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val calendarView = findViewById<CalendarView>(R.id.calendar_view)
        val selectedDate = findViewById<TextView>(R.id.tv_selected_date)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate.text = "Tasks for: $dayOfMonth/${month + 1}/$year"
        }
    }
}
