package com.esma.activitdenemesi.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.esma.activitdenemesi.R
import com.esma.activitdenemesi.fragments.auth.LoginFragment
import com.esma.activitdenemesi.fragments.auth.RegisterFragment

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // İlk fragment olarak LoginFragment'i göster
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.authFragmentContainer, LoginFragment())
                .commit()
        }
    }

    fun navigateToRegister() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, RegisterFragment())
            .addToBackStack(null) // Geri tuşu ile dönebilmek için
            .commit()
    }

    // RegisterFragment'ten LoginFragment'e geçiş
    fun navigateToLogin() {
        supportFragmentManager.popBackStack() // Geri git
    }
}