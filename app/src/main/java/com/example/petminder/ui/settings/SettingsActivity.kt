package com.example.petminder.ui.settings

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.petminder.R
import com.example.petminder.ui.calendar.CalendarScreenActivity
import com.example.petminder.ui.home.HomeDailyTaskActivity
import com.example.petminder.ui.pets.PetListActivity

class SettingsActivity : AppCompatActivity() {

    private val PICK_IMAGE = 101

    private lateinit var uploadIcon: ImageView
    private lateinit var profileName: TextView
    private lateinit var inputBirthday: com.google.android.material.chip.Chip
    private lateinit var petChipGroup: com.google.android.material.chip.ChipGroup
    private lateinit var inputEmail: EditText
    private lateinit var switchDailyTasks: SwitchCompat
    private lateinit var switchHealthUpdates: SwitchCompat
    private lateinit var inputNotes: EditText
    private lateinit var bottomNav: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setupViews()
        setupListeners()
        setupBottomNavigation()
    }

    private fun setupViews() {
        uploadIcon = findViewById(R.id.uploadIcon)
        profileName = findViewById(R.id.profile_name)
        inputBirthday = findViewById(R.id.inputBirthday)
        petChipGroup = findViewById(R.id.petChipGroup)
        inputEmail = findViewById(R.id.inputEmail)
        switchDailyTasks = findViewById(R.id.switch_daily_tasks)
        switchHealthUpdates = findViewById(R.id.switch_healthUpdates)
        inputNotes = findViewById(R.id.inputNotes)
        bottomNav = findViewById(R.id.bottomNav)
    }

    private fun setupListeners() {

        // Upload the user's profile image
        uploadIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
        }

        // Example toggle behavior
        switchDailyTasks.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Daily Tasks: $isChecked", Toast.LENGTH_SHORT).show()
        }

        switchHealthUpdates.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Health Updates: $isChecked", Toast.LENGTH_SHORT).show()
        }
    }

    @Deprecated("Deprecated in Android 13+, but safe for backward compatibility")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            if (imageUri != null) {
                uploadIcon.setImageURI(imageUri)
            }
        }
    }

    private fun setupBottomNavigation() {

        // HOME
        bottomNav.findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeDailyTaskActivity::class.java))
        }

        // CALENDAR
        bottomNav.findViewById<LinearLayout>(R.id.navCalendar).setOnClickListener {
            startActivity(Intent(this, CalendarScreenActivity::class.java))
        }

        // PETS
        bottomNav.findViewById<LinearLayout>(R.id.navPets).setOnClickListener {
            startActivity(Intent(this, PetListActivity::class.java))
        }

        // SETTINGS (current screen) — do nothing
    }
}
