package com.example.unilocal.activities

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.unilocal.R
import com.example.unilocal.databinding.ActivityDetailPlaceBinding
import com.example.unilocal.db.*
import com.example.unilocal.fragment.CommentsPlaceFragment
import com.example.unilocal.model.City
import com.example.unilocal.model.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class DetailPlaceActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var binding:ActivityDetailPlaceBinding
    var codePlace:Int = 0
    var place:Place? = null
    lateinit var fragment:Fragment

    private lateinit var mapView: MapView
    private lateinit var google_map: GoogleMap
    private var tienePermiso = false
    private var placeLocation = LatLng(4.550923, -75.6557201)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_place)

        binding = ActivityDetailPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val placeName = findViewById<TextView>(R.id.place_name)

        codePlace = intent.extras!!.getInt("code")

        place = Places.get(codePlace)

        if(place != null){
            val ownerName = Users.findNameByID(place!!.idOwner)
            binding.ownerName.text = ownerName
            binding.placeName.text = place!!.name
            binding.placeDescription.text = place!!.description
            binding.placeDirection.text = place!!.direction

            val city = Cities.findByID(place!!.idCity)
            val department = Departments.findByID(place!!.idDepartment)
            val country = Countries.findByID(place!!.idCountry)

            binding.placeLocation.text = "$city, $department - $country"

            val statusPlace = place!!.isOpen()
            if(statusPlace == "Open"){
                binding.placeSchedule.setTextColor(Color.GREEN)
                binding.placeSchedule.text = getString(R.string.detail_place_open)
            } else {
                binding.placeSchedule.setTextColor(Color.RED)
                binding.placeSchedule.text = getString(R.string.detail_place_closed)
            }

            var phoneNumbers = ""

            if(place!!.phoneNumbers.isNotEmpty()){
                for (tel in place!!.phoneNumbers) {
                    phoneNumbers += "$tel, "
                }
                phoneNumbers = phoneNumbers.substring(0, phoneNumbers.length - 2)
            }

            binding.placePhoneNumbers.text = phoneNumbers

            var schedules = ""

            for (schedule in place!!.schedules){
                for (day in schedule.weekDay){
                    schedules += "$day: ${schedule.startTime}:00 - ${schedule.closingTime}:00\n"
                }
            }
            binding.placeSchedules.text = schedules
            binding.placeRating.text = place!!.getRatingAverage(Comments.listById(place!!.id)).toString()

        }

        fragment= CommentsPlaceFragment.newInstance(codePlace)

        binding.comments.setOnClickListener {
            if(fragment.isVisible){
                hideFragment()
            } else {
                showFragment()
            }
        }
        /*
        placeLocation = LatLng(place!!.latitude, place!!.longitude)
        */

        mapView = findViewById(R.id.place_map_location)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


    private fun showFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.comments_view, fragment)
        fragmentTransaction.commit()
    }

    private fun hideFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
    }

    companion object {



    }

    override fun onMapReady(googleMap: GoogleMap) {
        google_map = googleMap
        google_map.moveCamera( CameraUpdateFactory.newLatLngZoom(placeLocation, 17f))

        google_map.uiSettings.isZoomControlsEnabled = true

    }
}