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
import android.widget.*
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
    var init:Boolean = true
    lateinit var btn_next: Button

    //FORM 1 VARS
    lateinit var input_place_name:EditText
    lateinit var input_place_description:EditText
    lateinit var input_open_hour:EditText
    lateinit var input_close_hour:EditText
    lateinit var input_place_phone:EditText
    lateinit var input_place_secundary_phone:EditText
    lateinit var input_place_adress:EditText


    lateinit var place_name:String
    lateinit var place_description:String
    lateinit var open_hour:String
    lateinit var close_hour:String
    lateinit var place_phone:String
    lateinit var place_secundary_phone:String
    lateinit var place_adress:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.formPager
        viewPager.offscreenPageLimit = 3
        layouts = intArrayOf(
            R.layout.activity_register_place_form_1,
            R.layout.activity_register_place_form_2,
            R.layout.activity_register_place_form_3
        )

        adapter = Adapter(this, layouts)
        viewPager.adapter = adapter

        btn_next = binding.Next

        btn_next.setOnClickListener{
            if( btn_next.text == getString(R.string.register_user_finish)){
                //register()
            }else if (btn_next.text == getString(R.string.register_user_choose_photo)){
                //askImages()
            }else{
                nextListener()
            }
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {


            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(init){
                    if(position == 0){
                        initVars()
                        input_open_hour.setOnClickListener{
                            showTimePickerDialog(input_open_hour)
                        }
                        input_close_hour.setOnClickListener{
                            showTimePickerDialog(input_close_hour)
                        }
                        init = false
                    }
                }

            }

            override fun onPageSelected(position: Int) {

                if(position == 1){
                    verifyForm1Inputs()
                }
                if(position==2) {

                }else{
                }
            }
            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    private fun verifyForm1Inputs(){
        getInputsText()
        verifyRegexPhone()
        /*verifyRegexEmail()
        verifyRegexPass()
        verifyDatesWithDb()*/
        if(place_name.isEmpty()){
            input_place_name.error = getString(R.string.forgot_msg_obligatorie_inputs)
        }
        if(place_description.isEmpty()){
            input_place_description.error = getString(R.string.forgot_msg_obligatorie_inputs)
        }
        if(open_hour.isEmpty()){
            input_open_hour.error = getString(R.string.forgot_msg_obligatorie_inputs)
        }
        if(close_hour.isEmpty()){
            input_close_hour.error = getString(R.string.forgot_msg_obligatorie_inputs)
        }
        if(place_phone.isEmpty()){
            input_place_phone.error = getString(R.string.forgot_msg_obligatorie_inputs)
        }
        if(place_secundary_phone.isEmpty()){
            input_place_secundary_phone.error = getString(R.string.forgot_msg_obligatorie_inputs)
        }
        if(place_adress.isEmpty()){
            input_place_adress.error = getString(R.string.forgot_msg_obligatorie_inputs)
        }
    }

    private fun getInputsText() {
        //FORM 1 INPUTS
        place_name = input_place_name.text.toString()
        place_description = input_place_description.text.toString()
        open_hour = input_open_hour.text.toString()
        close_hour = input_close_hour.text.toString()
        place_phone = input_place_phone.text.toString()
        place_secundary_phone = input_place_secundary_phone.text.toString()
        place_adress = input_place_adress.text.toString()
    }

    private fun initVars(){
        //FORM 1 VARS
        input_place_name = viewPager.findViewById(R.id.place_name)
        input_place_description = viewPager.findViewById(R.id.place_description)
        input_open_hour = viewPager.findViewById(R.id.place_open_hour)
        input_close_hour = viewPager.findViewById(R.id.place_close_hour)
        input_place_phone = viewPager.findViewById(R.id.place_phone)
        input_place_secundary_phone = viewPager.findViewById(R.id.place_secundary_phone)
        input_place_adress = viewPager.findViewById(R.id.place_adress)

        //FORM 2 VARS

        //FORM 3 VARS

        //FORM 4 VARS
    }

    fun nextListener (){
        if (viewPager.currentItem+1 < layouts.size){
            viewPager.setCurrentItem(viewPager.currentItem+1)
        }
    }

    private fun showTimePickerDialog(view: EditText) {
        val timePicker = TimePickerFragment {onTimeSelected(it, view)}
        timePicker.show(supportFragmentManager, "time")
    }

    private fun onTimeSelected (time:String, view:EditText){{}
        view.setText("$time")
    }

    private fun verifyRegexPhone(): Boolean {
        if(place_phone.length == 10 && place_secundary_phone.length == 10){
            return true
        }else{
            if(place_phone.length != 10){
                input_place_phone.error = getString(R.string.register_user_msg_invalid_phone)
            }
            if(place_secundary_phone.length != 10){
                input_place_secundary_phone.error = getString(R.string.register_user_msg_invalid_phone)
            }
            return false
        }


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