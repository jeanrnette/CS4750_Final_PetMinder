package com.example.petminder.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class LoginActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var txtSignUp: TextView
    private lateinit var txtForgotPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        btnSignIn = findViewById(R.id.btnSignIn)
        txtSignUp = findViewById(R.id.txtSignUp)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
    }

    private fun setupListeners() {

        // Sign In Button
        btnSignIn.setOnClickListener {
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            // TODO: Add real validation or ViewModel login call
            if (email.isNotEmpty() && password.isNotEmpty()) {

                // Example: navigate to your Home or Main Activity
                // Update this to your real next screen
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                inputEmail.error = "Required"
                inputPassword.error = "Required"
            }
        }

        // Navigate to Sign Up screen
        txtSignUp.setOnClickListener {
            // TODO: Replace with your SignUpActivity
            // startActivity(Intent(this, SignUpActivity::class.java))
        }

        // Forgot Password tapped
        txtForgotPassword.setOnClickListener {
            // TODO: Replace with ForgotPasswordActivity
        }
    }
}
