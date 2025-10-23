package com.esma.activitdenemesi.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.esma.activitdenemesi.R
import com.esma.activitdenemesi.databinding.ActivityMainBinding
import com.esma.activitdenemesi.fragments.main.FavoritesFragment
import com.esma.activitdenemesi.fragments.main.HomeFragment
import com.esma.activitdenemesi.fragments.main.ProfileFragment
import com.esma.activitdenemesi.fragments.main.RentHistoryFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Kullanıcı kontrolü
        if (auth.currentUser == null) {
            navigateToAuth()
            return
        }

        setupBottomNavigation()

        // İlk fragment olarak HomeFragment göster
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_rent_history -> {
                    loadFragment(RentHistoryFragment())
                    true
                }
                R.id.nav_favorite -> {
                    loadFragment(FavoritesFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainer, fragment)
            .commit()
    }

    private fun navigateToAuth() {
        val intent = Intent(this, UserActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    // Çıkış yapma fonksiyonu (ProfileFragment'ten çağrılabilir)
    fun logout() {
        auth.signOut()
        navigateToAuth()
    }
}