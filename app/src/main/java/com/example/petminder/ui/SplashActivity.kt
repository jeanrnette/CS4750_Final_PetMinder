package com.example.petminder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Splash delay before going to next screen (2 seconds)
        Handler(Looper.getMainLooper()).postDelayed({

            // TODO: Change "MainActivity" to your real next screen
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 2000) // 2000 ms = 2 seconds
    }
}
