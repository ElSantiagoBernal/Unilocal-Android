package com.example.unilocal.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.unilocal.R
import com.example.unilocal.databinding.ActivityDetailPlaceBinding
import com.example.unilocal.db.*
import com.example.unilocal.model.City
import com.example.unilocal.model.Place

class DetailPlaceActivity : AppCompatActivity() {

    lateinit var binding:ActivityDetailPlaceBinding
    var codePlace:Int = 0
    var place:Place? = null


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
                binding.placeSchedule.text = "Abierto"
            } else {
                binding.placeSchedule.setTextColor(Color.RED)
                binding.placeSchedule.text = "Cerrado"
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

        }
    }

    companion object{

    }
}