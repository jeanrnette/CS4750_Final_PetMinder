package com.example.petminder.ui.pets

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R
import com.example.petminder.data.pet.PetEntity
import com.example.petminder.ui.pets.viewmodel.PetViewModel

class AddPetActivity : AppCompatActivity() {

    private val PICK_IMAGE = 100
    private var selectedImageUri: String? = null

    private val petViewModel: PetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pet)

        setupBackButton()
        setupImagePicker()
        setupSaveButton()
    }

    // ============================
    // BACK BUTTON
    // ============================
    private fun setupBackButton() {
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    // ============================
    // IMAGE PICKER
    // ============================
    private fun setupImagePicker() {
        val picker = findViewById<ImageView>(R.id.petImagePicker)
        picker.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
        }
    }

    @Deprecated("Deprecated, but required for gallery results on older APIs")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val uri: Uri? = data.data
            if (uri != null) {
                selectedImageUri = uri.toString()

                val preview = findViewById<ImageView>(R.id.petImagePreview)
                preview.visibility = ImageView.VISIBLE
                preview.setImageURI(uri)
            }
        }
    }

    // ============================
    // SAVE BUTTON
    // ============================
    private fun setupSaveButton() {
        val btnSavePet = findViewById<Button>(R.id.btnSavePet)

        btnSavePet.setOnClickListener {

            // Collect form input
            val name = findViewById<EditText>(R.id.inputPetName).text.toString().trim()
            val birthday = findViewById<EditText>(R.id.inputBirthday).text.toString().trim()
            val type = findViewById<EditText>(R.id.inputType).text.toString().trim()
            val weight = findViewById<EditText>(R.id.inputWeight).text.toString().trim()
            val vaccinated = findViewById<EditText>(R.id.inputVaccine).text.toString().trim()
            val sex = findViewById<EditText>(R.id.inputSex).text.toString().trim()
            val breed = findViewById<EditText>(R.id.inputBreed).text.toString().trim()
            val notes = findViewById<EditText>(R.id.inputNotes).text.toString().trim()
            val age = findViewById<EditText>(R.id.inputAge).text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your pet's name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create PetEntity
            val newPet = PetEntity(
                name = name,
                type = type,
                age = age,
                birthday = birthday,
                weight = weight,
                height = "",               // optional
                vaccines = vaccinated,
                imageUri = selectedImageUri
            )

            // Insert into database
            petViewModel.addPet(newPet)

            // Wait for pet to be inserted and navigate to details screen
            petViewModel.allPets.observe(this) { pets ->
                if (pets.isNotEmpty()) {

                    // Last added pet
                    val addedPet = pets.last()

                    val intent = Intent(this, PetDetailsActivity::class.java)
                    intent.putExtra("petId", addedPet.id)
                    startActivity(intent)

                    finish()  // close AddPetActivity
                }
            }
        }
    }
}
