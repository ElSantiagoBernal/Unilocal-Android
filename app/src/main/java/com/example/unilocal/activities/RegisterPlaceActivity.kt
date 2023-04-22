package com.example.unilocal.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.viewpager.widget.ViewPager
import com.example.unilocal.Adapter
import com.example.unilocal.R
import com.example.unilocal.databinding.ActivityRegisterPlaceBinding
import com.example.unilocal.db.Users
import com.example.unilocal.fragment.TimePickerFragment
import com.example.unilocal.model.User

class RegisterPlaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPlaceBinding
    lateinit var viewPager: ViewPager
    lateinit var layouts:IntArray
    lateinit var adapter: Adapter
    lateinit var userEmail:String
    private val PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123
    lateinit var user:User
    var init:Boolean = false
    lateinit var open_hour:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.formPager

        layouts = intArrayOf(
            R.layout.activity_register_place_form_1,
            R.layout.activity_register_place_form_2,
            R.layout.activity_register_place_form_3
        )

        adapter = Adapter(this, layouts)
        viewPager.adapter = adapter



        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {

                if(position == 0){
                    initVars()
                }
                if(position==2) {

                }else{

                }
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun initVars(){
        open_hour = viewPager.findViewById(R.id.place_open_hour)
        open_hour.setOnClickListener{
            showTimePickerDialog()
        }

    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment {onTimeSelected(it)}
        timePicker.show(supportFragmentManager, "time")
    }

    private fun onTimeSelected (time:String){
        viewPager.findViewById<EditText>(R.id.place_open_hour).setText("$time")
    }


    /*private fun initDots() {
        for (i in 0 until (viewPager.adapter?.count ?: 0)) {
            val dot = TextView(this)
            dot.text = "•"
            dot.textSize = 30f
            dot.setTextColor(ContextCompat.getColor( this, R.color.inactive))
            dot.setOnClickListener { viewPager.currentItem = i }
            dots.add(dot)
            binding.positionLayout.addView(dot)
        }
        dots.first().setTextColor(ContextCompat.getColor(this, R.color.active))
    }*/
}