package com.esma.activitdenemesi.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esma.activitdenemesi.databinding.FragmentRentHistoryBinding

class RentHistoryFragment : Fragment() {

    private var _binding: FragmentRentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Kiralama geçmişi verilerini yükle
        loadRentHistory()
    }

    private fun loadRentHistory() {
        // Firebase'den kiralama geçmişini çek
        // RecyclerView'a yükle
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}