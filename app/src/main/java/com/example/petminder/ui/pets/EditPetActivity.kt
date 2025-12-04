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

class EditPetActivity : AppCompatActivity() {

    private val PICK_IMAGE = 101
    private val petViewModel: PetViewModel by viewModels()
    private var currentPet: PetEntity? = null
    private var newImageUri: String? = null
    private var petId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pet)

        petId = intent.getIntExtra("petId", -1)
        if (petId == -1) {
            finish()
            return
        }

        setupBackButton()
        setupImagePicker()
        observePet()
        setupSaveButton()
    }

    private fun observePet() {
        petViewModel.allPets.observe(this) { pets ->
            currentPet = pets.find { it.id == petId }
            currentPet?.let { fillPetData(it) }
        }
    }

    private fun fillPetData(pet: PetEntity) {

        // Image
        val petImage = findViewById<ImageView>(R.id.petImagePreview)
        if (pet.imageUri != null) {
            petImage.setImageURI(Uri.parse(pet.imageUri))
        } else {
            petImage.setImageResource(R.drawable.ic_pets)
        }

        // Inputs
        findViewById<EditText>(R.id.inputPetName).setText(pet.name)
        findViewById<EditText>(R.id.inputBirthday).setText(pet.birthday)
        findViewById<EditText>(R.id.inputType).setText(pet.type)
        findViewById<EditText>(R.id.inputWeight).setText(pet.weight)
        findViewById<EditText>(R.id.inputVaccine).setText(pet.vaccines)
        findViewById<EditText>(R.id.inputSex).setText(pet.height)
        findViewById<EditText>(R.id.inputBreed).setText("") // Breed not stored earlier
        findViewById<EditText>(R.id.inputNotes).setText("")
    }

    private fun setupImagePicker() {
        findViewById<ImageView>(R.id.petImagePicker).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val uri = data?.data ?: return
            newImageUri = uri.toString()
            val preview = findViewById<ImageView>(R.id.petImagePreview)
            preview.setImageURI(uri)
        }
    }

    private fun setupSaveButton() {
        findViewById<Button>(R.id.btnSaveChanges).setOnClickListener {

            val updatedPet = currentPet?.copy(
                name = findViewById<EditText>(R.id.inputPetName).text.toString(),
                birthday = findViewById<EditText>(R.id.inputBirthday).text.toString(),
                type = findViewById<EditText>(R.id.inputType).text.toString(),
                weight = findViewById<EditText>(R.id.inputWeight).text.toString(),
                vaccines = findViewById<EditText>(R.id.inputVaccine).text.toString(),
                height = findViewById<EditText>(R.id.inputSex).text.toString(),
                imageUri = newImageUri ?: currentPet?.imageUri
            )

            if (updatedPet != null) {
                petViewModel.updatePet(updatedPet)

                val intent = Intent(this, PetDetailsActivity::class.java)
                intent.putExtra("petId", updatedPet.id)
                startActivity(intent)

                finish()
            }
        }
    }

    private fun setupBackButton() {
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
