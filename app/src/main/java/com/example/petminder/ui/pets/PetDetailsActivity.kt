package com.example.petminder.ui.pets

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R
import com.example.petminder.data.pet.PetEntity
import com.example.petminder.ui.calendar.CalendarScreenActivity
import com.example.petminder.ui.home.HomeDailyTaskActivity
import com.example.petminder.ui.settings.SettingsActivity
import com.example.petminder.ui.pets.viewmodel.PetViewModel

class PetDetailsActivity : AppCompatActivity() {

    private val petViewModel: PetViewModel by viewModels()
    private var currentPet: PetEntity? = null
    private var petId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_details)

        petId = intent.getIntExtra("petId", -1)

        if (petId == -1) {
            Toast.makeText(this, "Error loading pet", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setupBackButton()
        setupEditButton()
        setupBottomNav()
        observePetData()
    }

    private fun observePetData() {
        petViewModel.allPets.observe(this) { pets ->
            val pet = pets.find { it.id == petId }
            pet?.let {
                currentPet = it
                updateUI(it)
            }
        }
    }

    private fun updateUI(pet: PetEntity) {

        // Image
        val img = findViewById<ImageView>(R.id.petImage)
        if (pet.imageUri != null) {
            img.setImageURI(Uri.parse(pet.imageUri))
        } else {
            img.setImageResource(R.drawable.ic_pets)
        }

        // Header Title
        findViewById<TextView>(R.id.titleText).text = pet.name.uppercase()

        // Name
        findViewById<TextView>(R.id.petName).text = pet.name

        // Birthday
        findViewById<TextView>(R.id.birthdayChip).text =
            if (pet.birthday.isNotEmpty()) pet.birthday else "Unknown"

        // Notes
        findViewById<EditText>(R.id.petNotes).setText(pet.vaccines)

        // Attributes
        updateAttributes(pet)
    }

    private fun updateAttributes(pet: PetEntity) {
        val row1 = findViewById<LinearLayout>(R.id.attributesRow1)
        val row2 = findViewById<LinearLayout>(R.id.attributesRow2)

        row1.removeAllViews()
        row2.removeAllViews()

        // Attributes Row 1
        addChip(row1, pet.type)
        addChip(row1, pet.weight)
        addChip(row1, pet.vaccines)

        // Attributes Row 2
        addChip(row2, pet.height)
    }

    private fun addChip(parent: LinearLayout, value: String?) {
        if (value.isNullOrBlank()) return

        val chip = TextView(this)
        chip.text = "🐾 $value"
        chip.setPadding(24, 12, 24, 12)
        chip.textSize = 14f
        chip.setBackgroundResource(R.drawable.chip_background)
        chip.setTextColor(resources.getColor(android.R.color.black))

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 8, 8, 8)
        chip.layoutParams = params

        parent.addView(chip)
    }


    private fun setupEditButton() {
        findViewById<ImageView>(R.id.btnEdit).setOnClickListener {
            val intent = Intent(this, EditPetActivity::class.java)
            intent.putExtra("petId", petId)
            startActivity(intent)
        }
    }

    private fun setupBackButton() {
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private fun setupBottomNav() {

        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeDailyTaskActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.navCalendar).setOnClickListener {
            startActivity(Intent(this, CalendarScreenActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.navSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
