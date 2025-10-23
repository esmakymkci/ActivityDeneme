package com.esma.activitdenemesi.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.esma.activitdenemesi.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        auth = FirebaseAuth.getInstance()

        // Firebase kullanıcıyı kontrol et
        Handler(Looper.getMainLooper()).postDelayed({
            checkUserStatus()
        }, 2000)

    }

    private fun checkUserStatus() {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            // Kullanıcı giriş yapmış -> MainActivity'ye git
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // Kullanıcı giriş yapmamış -> AuthActivity'ye git
            startActivity(Intent(this, UserActivity::class.java))
        }
        finish() // SplashActivity'yi kapat
    }
}