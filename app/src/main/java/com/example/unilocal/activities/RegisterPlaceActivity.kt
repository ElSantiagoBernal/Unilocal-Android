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
import com.example.unilocal.model.User

class RegisterPlaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPlaceBinding
    lateinit var viewPager: ViewPager
    lateinit var layouts:IntArray
    lateinit var adapter: Adapter
    lateinit var userEmail:String
    private val PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123
    lateinit var user:User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sp = getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val email = sp.getString("email_user", "").toString()

        user = Users.findByEmail(email)!!

        if(user != null){
            Toast.makeText(this, "uri: ${user.imgUrl}", Toast.LENGTH_SHORT).show()
            binding.imgUser.setImageURI(Uri.parse(user.imgUrl))
        }else{
            Toast.makeText(this, "Es null", Toast.LENGTH_SHORT).show()
        }





        //viewPager = binding.formPager

        layouts = intArrayOf(

        )

        /*adapter = Adapter(this, layouts, binding_form_3)
        binding.formPager.adapter = adapter

        initDots()*/

    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        Toast.makeText(this, "3", Toast.LENGTH_SHORT).show()
        if(result.resultCode == RESULT_OK){
            var uri = Uri.parse(user.imgUrl)
            val inputStream = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            binding.imgUser.setImageBitmap(bitmap)
        }

    }
    private fun pickPhotoFromGallery() {
        val intent = Intent (Intent.ACTION_OPEN_DOCUMENT)
        Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
        intent.type = "image/*"
        Toast.makeText(this, "2", Toast.LENGTH_SHORT).show()
        startForActivityGallery.launch(intent)
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