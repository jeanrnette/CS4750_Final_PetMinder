package com.example.petminder.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var inputFullName: EditText
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var inputConfirmPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var txtSignIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        inputFullName = findViewById(R.id.inputFullName)
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        txtSignIn = findViewById(R.id.txtSignIn)
    }

    private fun setupListeners() {

        // SIGN UP BUTTON
        btnSignUp.setOnClickListener {
            val fullname = inputFullName.text.toString().trim()
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()
            val confirmPassword = inputConfirmPassword.text.toString().trim()

            // Basic validation
            if (fullname.isEmpty()) {
                inputFullName.error = "Required"
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                inputEmail.error = "Required"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                inputPassword.error = "Required"
                return@setOnClickListener
            }
            if (confirmPassword.isEmpty()) {
                inputConfirmPassword.error = "Required"
                return@setOnClickListener
            }
            if (password != confirmPassword) {
                inputConfirmPassword.error = "Passwords do not match"
                return@setOnClickListener
            }

            // TODO: Save account info in database or ViewModel

            // After sign-up, move to next onboarding screen
            val intent = Intent(this, PetInfoActivity1::class.java)
            startActivity(intent)
            finish()
        }

        // "Sign In" Click
        txtSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
