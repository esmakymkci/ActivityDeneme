package com.esma.activitdenemesi.fragments.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.esma.activitdenemesi.activities.MainActivity
import com.esma.activitdenemesi.activities.UserActivity
import com.esma.activitdenemesi.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        setupClickListeners()

    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInput(email, password)) {
                loginUser(email, password)
            }
        }

        binding.tvRegister.setOnClickListener {
            (activity as? UserActivity)?.navigateToRegister()
            //Bu fragment'ın içinde bulunduğu Activity'nin bir UserActivity olup olmadığını kontrol eder.
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.tilEmail.error = "E-posta gerekli"
            return false
        }
        if (password.isEmpty()) {
            binding.tilPassword.error = "Şifre gerekli"
            return false
        }
        if (password.length < 6) {
            binding.tilPassword.error = "Şifre en az 6 karakter olmalı"
            return false
        }
        return true
    }

    private fun loginUser(email: String, password: String) {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                binding.progressBar.visibility = View.GONE
                binding.btnLogin.isEnabled = true

                if (task.isSuccessful) {
                    Toast.makeText(context, "Giriş başarılı!", Toast.LENGTH_SHORT).show()
                    navigateToMainActivity()
                } else {
                    Toast.makeText(
                        context,
                        "Hata: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    //Intent(...): Android'de ekranlar (Activity) arası geçiş yapmak için kullanılır.
    //intent.flags = ...: Bu iki "flag" çok önemlidir. Bunlar, yeni Activity'yi başlatırken
    // "geri tuşuna basıldığında giriş ekranına dönülmesini engellemek için"
    // önceki tüm Activity'leri (giriş ve kayıt ekranları gibi) yığından (stack) temizler.

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}