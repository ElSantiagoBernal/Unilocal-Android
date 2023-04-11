package com.example.unilocal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.unilocal.R
import com.example.unilocal.databinding.ActivityDetailPlaceBinding
import com.example.unilocal.db.Places

class DetailPlaceActivity : AppCompatActivity() {

    lateinit var binding:ActivityDetailPlaceBinding
    var codePlace:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        codePlace = intent.extras!!.getInt("code")

        val place = Places.get(codePlace)

        Toast.makeText(this, "${place.toString()}", Toast.LENGTH_LONG).show()
    }
}