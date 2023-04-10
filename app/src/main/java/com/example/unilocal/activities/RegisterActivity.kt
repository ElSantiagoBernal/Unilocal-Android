package com.example.unilocal.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.unilocal.Adapter
import com.example.unilocal.R
import com.example.unilocal.databinding.ActivityRegisterBinding
import com.example.unilocal.databinding.ActivityRegisterFormUser1Binding
import com.example.unilocal.databinding.ActivityRegisterFormUser2Binding
import com.example.unilocal.databinding.ActivityRegisterFormUser3Binding
import com.example.unilocal.db.Users
import com.example.unilocal.model.User
import com.example.unilocal.ui.login.LoginActivity
import kotlin.properties.Delegates

class RegisterActivity : AppCompatActivity() {



    lateinit var viewPager: ViewPager
    lateinit var layouts:IntArray
    lateinit var adapter: Adapter
    private lateinit var binding: ActivityRegisterBinding
    lateinit var binding_form_1: ActivityRegisterFormUser1Binding
    lateinit var binding_form_2: ActivityRegisterFormUser2Binding
    lateinit var binding_form_3: ActivityRegisterFormUser3Binding
    lateinit var imageUrl:String
    var clics:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        binding_form_1 = ActivityRegisterFormUser1Binding.inflate(layoutInflater)
        binding_form_2 = ActivityRegisterFormUser2Binding.inflate(layoutInflater)
        binding_form_3 = ActivityRegisterFormUser3Binding.inflate(layoutInflater)
        setContentView(binding.root)


        viewPager = binding.formPager
        viewPager.offscreenPageLimit = 3

        layouts = intArrayOf(
            R.layout.activity_register_form_user_1,
            R.layout.activity_register_form_user_2,
            R.layout.activity_register_form_user_3

        )
        adapter = Adapter(this, layouts)
        binding.formPager.adapter = adapter

        binding_form_3.btnChooseImg.setOnClickListener{
            Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()

        }

        binding.Next.setOnClickListener{
            askImages()
            nextListener()
        }

        binding_form_1.userName.setOnClickListener{
            Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
        }

        binding.Next.setOnClickListener{
            clics++
            if(clics == 2){
                binding.Next.text = "Terminar"
            }else if( binding.Next.text == "Terminar"){
                register()
            }
            nextListener()
        }

        /*viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // No hacer nada
            }

            override fun onPageSelected(position: Int) {
                // Bloquear el cambio de página si se intenta avanzar
                if (position > 0) {
                    viewPager.setCurrentItem(0, true)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                // No hacer nada
            }
        })*/







    }

    private fun register() {
        //FORM REGISTER 1
        var names = binding.formPager.findViewById<EditText>(R.id.user_name).text.toString()
        var last_names = binding.formPager.findViewById<EditText>(R.id.user_last_names).text.toString()
        var email = binding.formPager.findViewById<EditText>(R.id.user_name).text.toString()
        var user = binding.formPager.findViewById<EditText>(R.id.user_last_names).text.toString()
        var pass = binding.formPager.findViewById<EditText>(R.id.user_name).text.toString()

        //FORM REGISTER 2

        var phone = binding.formPager.findViewById<EditText>(R.id.user_phone).text.toString()
        var country = binding.formPager.findViewById<EditText>(R.id.user_country).text.toString()
        var department = binding.formPager.findViewById<EditText>(R.id.user_department).text.toString()
        var city = binding.formPager.findViewById<EditText>(R.id.user_city).text.toString()
        var age = binding.formPager.findViewById<EditText>(R.id.user_age).text.toString()

        //FORM REGISTER 3



        if(names.isNotEmpty() && last_names.isNotEmpty() && email.isNotEmpty() && user.isNotEmpty() && pass.isNotEmpty()
            && phone.isNotEmpty() && country.isNotEmpty() && department.isNotEmpty() && city.isNotEmpty() && age.isNotEmpty()
            /*&& imageUrl.isNotEmpty()*/){
            val user = User(Users.size()+1, names, last_names, email, user, pass, 1, 1, 1, age.toInt(), "aee")
            Users.add(user)
            Toast.makeText(this, "Se registró", Toast.LENGTH_SHORT).show()
            goToLogIn()
        }else{
            Toast.makeText(this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show()
        }
    }

    fun goToLogIn(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
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

        if(result.resultCode == RESULT_OK){
            val data = result.data?.data
            //val btn = binding.formPager.findViewById<Button>(R.id.btn_choose_img)
            imageUrl = data.toString()
            binding_form_3.btnChooseImg.setImageURI(data)

        }

    }

    private fun pickPhotoFromGallery() {
        val intent = Intent (Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityGallery.launch(intent)
    }


}

