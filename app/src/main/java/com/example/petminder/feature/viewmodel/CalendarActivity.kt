package com.example.petminder.feature.calendar

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_screen)

        // ---- BUILD STATIC NOVEMBER 2025 CALENDAR GRID ----
        val days = listOf(

            // WEEK 1 (inactive 26–31)
            DayCell(R.id.d26, 26, inactive = true),
            DayCell(R.id.d27, 27, inactive = true),
            DayCell(R.id.d28, 28, inactive = true),
            DayCell(R.id.d29, 29, inactive = true),
            DayCell(R.id.d30, 30, inactive = true),
            DayCell(R.id.d31, 31, inactive = true),
            DayCell(R.id.d1, 1, red = true),

            // WEEK 2
            DayCell(R.id.d2, 2),
            DayCell(R.id.d3, 3),
            DayCell(R.id.d4, 4),
            DayCell(R.id.d5, 5, blue = true),
            DayCell(R.id.d6, 6),
            DayCell(R.id.d7, 7),
            DayCell(R.id.d8, 8),

            // WEEK 3
            DayCell(R.id.d9, 9),
            DayCell(R.id.d10, 10),
            DayCell(R.id.d11, 11, red = true),
            DayCell(R.id.d12, 12, blue = true),
            DayCell(R.id.d13, 13),
            DayCell(R.id.d14, 14),
            DayCell(R.id.d15, 15),

            // WEEK 4
            DayCell(R.id.d16, 16),
            DayCell(R.id.d17, 17),
            DayCell(R.id.d18, 18),
            DayCell(R.id.d19, 19, blue = true),
            DayCell(R.id.d20, 20, selected = true),
            DayCell(R.id.d21, 21),
            DayCell(R.id.d22, 22),

            // WEEK 5
            DayCell(R.id.d23, 23),
            DayCell(R.id.d24, 24),
            DayCell(R.id.d25, 25),
            DayCell(R.id.d26b, 26, blue = true),
            DayCell(R.id.d27b, 27),
            DayCell(R.id.d28b, 28),
            DayCell(R.id.d29b, 29),

            // WEEK 6
            DayCell(R.id.d30, 30),
            DayCell(R.id.d1n, 1, inactive = true),
            DayCell(R.id.d2n, 2, inactive = true),
            DayCell(R.id.d3n, 3, blue = true),
            DayCell(R.id.d4n, 4, inactive = true),
            DayCell(R.id.d5n, 5, inactive = true),
            DayCell(R.id.d6n, 6, inactive = true)
        )

        // ---- APPLY STYLING ----
        styleCalendar(days)
    }


    // --------------------------------------------------------------------
    //  DATA MODEL FOR EACH CALENDAR CELL
    // --------------------------------------------------------------------
    data class DayCell(
        val id: Int,
        val number: Int,
        val inactive: Boolean = false,
        val red: Boolean = false,
        val blue: Boolean = false,
        val selected: Boolean = false
    )


    // --------------------------------------------------------------------
    // APPLY COLORS + NUMBER + BACKGROUND TO EACH CELL
    // --------------------------------------------------------------------
    fun styleCalendar(days: List<DayCell>) {

        days.forEach { day ->

            val cell = findViewById<View>(day.id)
            val num = cell.findViewById<TextView>(R.id.dayNumber)
            val dot = cell.findViewById<View>(R.id.dayDot)
            val box = cell.findViewById<View>(R.id.dayContainer)

            // Set the number
            num.text = day.number.toString()

            // Inactive days (gray numbers, no dot)
            if (day.inactive) {
                num.setTextColor(Color.parseColor("#D6D6D6"))
                dot.visibility = View.INVISIBLE
                box.setBackgroundColor(Color.TRANSPARENT)
                return@forEach
            }

            // Active day defaults
            dot.visibility = View.VISIBLE
            num.setTextColor(Color.BLACK)

            // Apply colors
            when {
                day.red -> box.setBackgroundResource(R.drawable.bg_day_red)
                day.blue -> box.setBackgroundResource(R.drawable.bg_day_blue)
                day.selected -> box.setBackgroundResource(R.drawable.bg_day_selected)
                else -> box.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }
}
