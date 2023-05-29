package com.example.unilocal.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unilocal.R
import com.example.unilocal.activities.DetailPlaceActivity
import com.example.unilocal.adapter.PlaceAdapter
import com.example.unilocal.databinding.FragmentProfileBinding
import com.example.unilocal.db.Places
import com.example.unilocal.model.Comment
import com.example.unilocal.model.Place
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var listPlacesByOwner:ArrayList<Place>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        listPlacesByOwner = ArrayList()

        val adapter = PlaceAdapter(listPlacesByOwner)
        binding.listPlaces.adapter = adapter
        binding.listPlaces.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        val sp = requireActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val idUser = sp.getInt("id_user", -1)
        if( idUser != -1 ) {
            Firebase.firestore
                .collection("places")
                .whereEqualTo("idOwner", idUser)
                .get()
                .addOnSuccessListener {
                    if(it.isEmpty){
                        //AQUI MOSTRAR MENSAJE DE QUE NO HAY COMMENTS
                    }else{
                        for(doc in it){
                            val place = doc.toObject(Place::class.java)
                            if(place != null){
                                listPlacesByOwner.add(place)
                                adapter.notifyItemInserted(listPlacesByOwner.size-1)
                            }
                        }
                    }
                }
        }

        return binding.root
    }

}