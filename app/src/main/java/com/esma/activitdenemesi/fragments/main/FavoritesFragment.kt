package com.esma.activitdenemesi.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esma.activitdenemesi.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {


    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Favori araçları yükle
        loadFavorites()
    }

    private fun loadFavorites() {
        // Firebase'den favori araçları çek
        // RecyclerView'a yükle
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}