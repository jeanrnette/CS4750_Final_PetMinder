package com.example.petminder.ui.onboarding

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petminder.R

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val txtSignIn = findViewById<TextView>(R.id.txtSignIn)

        btnSignUp.setOnClickListener {
            // TODO: Add create account logic
        }

        txtSignIn.setOnClickListener {
            finish()
        }
    }
}
