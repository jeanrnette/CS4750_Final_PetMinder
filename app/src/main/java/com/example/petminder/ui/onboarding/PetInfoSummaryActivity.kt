package com.example.petminder.ui.onboarding

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.MainActivity
import com.example.petminder.R

class PetInfoSummaryActivity : AppCompatActivity() {

    private lateinit var imagePreview: ImageView
    private lateinit var uploadContainer: LinearLayout
    private lateinit var petNameText: TextView
    private lateinit var btnFinish: Button

    private val PICK_IMAGE = 100

    private var imageUri: Uri? = null

    private lateinit var petName: String
    private lateinit var species: String
    private lateinit var sex: String
    private lateinit var age: String
    private lateinit var birthday: String
    private lateinit var weight: String
    private lateinit var height: String
    private lateinit var vaccines: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_info_summary)

        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData() {
        petName = intent.getStringExtra("petName") ?: ""
        species = intent.getStringExtra("species") ?: ""
        sex = intent.getStringExtra("sex") ?: ""
        age = intent.getStringExtra("age") ?: ""
        birthday = intent.getStringExtra("birthday") ?: ""
        weight = intent.getStringExtra("weight") ?: ""
        height = intent.getStringExtra("height") ?: ""
        vaccines = intent.getStringExtra("vaccines") ?: ""
    }

    private fun setupViews() {
        imagePreview = findViewById(R.id.imagePreview)
        uploadContainer = findViewById(R.id.uploadContainer)
        petNameText = findViewById(R.id.petName)
        btnFinish = findViewById(R.id.btnFinish)

        // Set summary fields
        petNameText.text = petName
    }

    private fun setupListeners() {
        uploadContainer.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
        }

        btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity() // Clear onboarding stack
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            imagePreview.setImageURI(imageUri)
            imagePreview.visibility = ImageView.VISIBLE
        }
    }
}
