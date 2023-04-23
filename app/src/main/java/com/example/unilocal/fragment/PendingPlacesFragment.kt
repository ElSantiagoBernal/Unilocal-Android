package com.example.unilocal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unilocal.R
import com.example.unilocal.adapter.PlaceAdapter
import com.example.unilocal.databinding.FragmentPendingPlacesBinding
import com.example.unilocal.db.Places
import com.example.unilocal.model.Place
import com.example.unilocal.model.PlaceStatus


class PendingPlacesFragment : Fragment() {

    lateinit var binding:FragmentPendingPlacesBinding
    private var listPendingPlaces:ArrayList<Place> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPendingPlacesBinding.inflate(inflater, container, false)

        listPendingPlaces = Places.ListByState(PlaceStatus.PENDING)

        val adapter = PlaceAdapter(listPendingPlaces)
        binding.listPlaces.adapter = adapter
        binding.listPlaces.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}