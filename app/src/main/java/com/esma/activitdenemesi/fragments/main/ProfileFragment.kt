package com.esma.activitdenemesi.fragments.main

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esma.activitdenemesi.activities.MainActivity
import com.esma.activitdenemesi.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setupUI()
        setupClickListeners()
    }

    private fun setupUI() {
        val user = auth.currentUser
        binding.tvUserName.text = user?.displayName ?: "Kullanıcı"
        binding.tvUserEmail.text = user?.email ?: ""
    }

    private fun setupClickListeners() {
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }

        binding.btnEditProfile.setOnClickListener {
            // Profil düzenleme ekranına git
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Çıkış Yap")
            .setMessage("Çıkış yapmak istediğinize emin misiniz?")
            .setPositiveButton("Evet") { _, _ ->
                (activity as? MainActivity)?.logout()
            }
            .setNegativeButton("Hayır", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}