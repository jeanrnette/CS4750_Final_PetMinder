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
    private var imageUri: String? = null

    private val petViewModel: PetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pet)

        val petImagePicker = findViewById<ImageView>(R.id.petImagePicker)
        val petImagePreview = findViewById<ImageView>(R.id.petImagePreview)

        val inputName = findViewById<EditText>(R.id.inputPetName)
        val inputSpecies = findViewById<EditText>(R.id.inputSpecies)
        val inputSex = findViewById<EditText>(R.id.inputSex)
        val inputBirthday = findViewById<EditText>(R.id.inputBirthday)
        val inputAge = findViewById<EditText>(R.id.inputAge)
        val inputWeight = findViewById<EditText>(R.id.inputWeight)
        val inputVaccines = findViewById<EditText>(R.id.inputVaccines)

        val btnSave = findViewById<Button>(R.id.btnSavePet)

        petImagePicker.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
        }

        btnSave.setOnClickListener {

            val pet = PetEntity(
                name = inputName.text.toString(),
                type = inputSpecies.text.toString(),
                age = inputAge.text.toString(),
                birthday = inputBirthday.text.toString(),
                weight = inputWeight.text.toString(),
                height = "",
                vaccines = inputVaccines.text.toString(),
                imageUri = imageUri
            )

            petViewModel.addPet(pet)

            Toast.makeText(this, "Pet added!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    @Deprecated("Deprecated in Android 13+ but still needed for gallery result")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                imageUri = uri.toString()
                findViewById<ImageView>(R.id.petImagePreview).apply {
                    visibility = ImageView.VISIBLE
                    setImageURI(uri)
                }
            }
        }
    }
}
