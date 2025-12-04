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

class PetListActivity : AppCompatActivity() {

    private val petViewModel: PetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_list)

        setupBottomNav()
        setupAddPetButton()
        observePets()
    }

    private fun observePets() {
        val container = findViewById<LinearLayout>(R.id.dynamicPetContainer)
        val emptyMessage = findViewById<TextView>(R.id.emptyMessage)

        petViewModel.allPets.observe(this) { pets ->

            container.removeAllViews()

            if (pets.isEmpty()) {
                emptyMessage.visibility = TextView.VISIBLE
                return@observe
            }

            emptyMessage.visibility = TextView.GONE

            pets.forEach { pet ->
                val itemView = layoutInflater.inflate(R.layout.item_pet_card, container, false)

                itemView.findViewById<TextView>(R.id.txtName).text = pet.name
                itemView.findViewById<TextView>(R.id.txtAge).text = pet.age

                val imageView = itemView.findViewById<ImageView>(R.id.imgPet)

                if (pet.imageUri != null) {
                    imageView.setImageURI(Uri.parse(pet.imageUri))
                } else {
                    imageView.setImageResource(R.drawable.ic_pets)
                }

                itemView.setOnClickListener {
                    val intent = Intent(this, PetDetailsActivity::class.java)
                    intent.putExtra("petId", pet.id)
                    startActivity(intent)
                }

                container.addView(itemView)
            }
        }
    }

    private fun setupAddPetButton() {
        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.btnAddPet)
            .setOnClickListener {
                startActivity(Intent(this, AddPetActivity::class.java))
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
