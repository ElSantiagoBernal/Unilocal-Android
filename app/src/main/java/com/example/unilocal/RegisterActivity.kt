package com.example.unilocal

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.unilocal.databinding.ActivityRegisterBinding
import com.example.unilocal.databinding.ActivityRegisterFormUser1Binding
import com.example.unilocal.databinding.ActivityRegisterFormUser2Binding
import com.example.unilocal.databinding.ActivityRegisterFormUser3Binding

class RegisterActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var btnNext: Button
    lateinit var layouts:IntArray
    lateinit var adapter: Adapter
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var binding_form_1: ActivityRegisterFormUser1Binding
    private lateinit var binding_form_2: ActivityRegisterFormUser2Binding
    private lateinit var binding_form_3: ActivityRegisterFormUser3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        binding_form_1 = ActivityRegisterFormUser1Binding.inflate(layoutInflater)
        binding_form_2 = ActivityRegisterFormUser2Binding.inflate(layoutInflater)
        binding_form_3 = ActivityRegisterFormUser3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.formPager
        btnNext = binding.Next

        layouts = intArrayOf(
            R.layout.activity_register_form_user_1,
            R.layout.activity_register_form_user_2,
            R.layout.activity_register_form_user_3

        )

        adapter = Adapter(this, layouts)
        viewPager.adapter = adapter

        binding_form_3.btnChooseImg.setOnClickListener{
            Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
            askImages()
        }

        binding.Next.setOnClickListener{
            nextListener()
            askImages()
        }




    }
    fun nextListener (){
        if (viewPager.currentItem+1 < layouts.size){
            viewPager.setCurrentItem(viewPager.currentItem+1)
        }
    }

    fun askImages (){

        requestPermission()
    }

    private fun requestPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    pickPhotoFromGallery()
                }

                else -> requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }else{
            pickPhotoFromGallery()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted ->

        if(isGranted){
            pickPhotoFromGallery()
        }else{
            Toast.makeText(this, "Necesitas habilitar los permisos", Toast.LENGTH_SHORT).show()
        }

    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->

        if(result.resultCode == Activity.RESULT_OK){
            val data = result.data?.data

            binding.imageView.setImageURI(data)
            binding_form_3.btnChooseImg.setImageURI(data)

        }

    }

    private fun pickPhotoFromGallery() {
        val intent = Intent (Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityGallery.launch(intent)
    }


}

