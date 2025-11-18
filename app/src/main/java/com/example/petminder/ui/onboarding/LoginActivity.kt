package com.example.petminder.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signUpText = findViewById<TextView>(R.id.txtSignUp)
        val signInBtn = findViewById<Button>(R.id.btnSignIn)

        // Navigate to Sign Up (Create Account)
        signUpText.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }

        // Handle login click
        signInBtn.setOnClickListener {
            // TODO: handle login logic here
        }
    }
}
