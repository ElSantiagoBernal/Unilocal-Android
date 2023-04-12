package com.example.unilocal.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
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

class RegisterActivity : AppCompatActivity() {



    lateinit var viewPager: ViewPager
    lateinit var layouts:IntArray
    lateinit var adapter: Adapter
    private lateinit var binding: ActivityRegisterBinding
    lateinit var binding_form_1: ActivityRegisterFormUser1Binding
    lateinit var binding_form_2: ActivityRegisterFormUser2Binding
    lateinit var binding_form_3: ActivityRegisterFormUser3Binding
    lateinit var imageUrl:String
    val dots = mutableListOf<TextView>()

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
        adapter = Adapter(this, layouts, binding_form_3)
        binding.formPager.adapter = adapter

        initDots()


        binding.Next.setOnClickListener{
            //askImages()
            nextListener()
        }

        binding.Next.setOnClickListener{
            if( binding.Next.text == getString(R.string.register_user_finish)){
                register()
            }
            nextListener()
        }


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                dots.forEachIndexed { index, dot ->
                    dot.setTextColor(
                        ContextCompat.getColor( this@RegisterActivity,
                            if (index == position) R.color.active else R.color.inactive
                        )
                    )
                }
                if(position==2){
                    binding.Next.text = getString(R.string.register_user_choose_photo)
                }else if(imageUrl.isNotEmpty()) {
                    binding.Next.text = getString(R.string.register_user_finish)
                }else{
                    binding.Next.text = getString(R.string.register_user_next)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })




    }

    private fun register() {
        //FORM REGISTER 1
        var names = viewPager.findViewById<EditText>(R.id.user_name).text.toString()
        var last_names = viewPager.findViewById<EditText>(R.id.user_last_names).text.toString()
        var email = viewPager.findViewById<EditText>(R.id.user_name).text.toString()
        var user = viewPager.findViewById<EditText>(R.id.user_last_names).text.toString()
        var pass = viewPager.findViewById<EditText>(R.id.user_name).text.toString()

        //FORM REGISTER 2

        var phone = viewPager.findViewById<EditText>(R.id.user_phone).text.toString()
        var country = viewPager.findViewById<EditText>(R.id.user_country).text.toString()
        var department = viewPager.findViewById<EditText>(R.id.user_department).text.toString()
        var city = viewPager.findViewById<EditText>(R.id.user_city).text.toString()
        var age = viewPager.findViewById<EditText>(R.id.user_age).text.toString()

        //FORM REGISTER 3


        if(names.isNotEmpty() && last_names.isNotEmpty() && email.isNotEmpty() && user.isNotEmpty() && pass.isNotEmpty()
            && phone.isNotEmpty() && country.isNotEmpty() && department.isNotEmpty() && city.isNotEmpty() && age.isNotEmpty()
            /*&& imageUrl.isNotEmpty()*/){

            if(Users.findByEmail(email) == null){
                Toast.makeText(this, getString(R.string.register_user_msg_email_exists), Toast.LENGTH_SHORT).show()
            }else if(Users.findByUsername(user) == null){
                Toast.makeText(this, getString(R.string.register_user_msg_username_exists), Toast.LENGTH_SHORT).show()
            }else if(Users.findByPhone(phone) == null){
                Toast.makeText(this, getString(R.string.register_user_msg_phone_exists), Toast.LENGTH_SHORT).show()
            }else{
                val user = User(Users.size()+1, names, last_names, email, user, pass, 1, 1, 1, age.toInt(), "aee", phone)
                Users.add(user)
                Toast.makeText(this, getString(R.string.register_user_msg_user_registered), Toast.LENGTH_SHORT).show()
                goToLogIn()
            }
        }else{
            Toast.makeText(this, getString(R.string.register_user_msg_all_inpts_obligatories), Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, getString(R.string.register_user_msg_allow_permissions), Toast.LENGTH_SHORT).show()
        }

    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->

        if(result.resultCode == RESULT_OK){
            val data = result.data?.data
            val btn = binding.formPager.findViewById<ImageButton>(R.id.btn_choose_img)
            imageUrl = data.toString()
            btn.setImageURI(data)

        }

    }

    private fun pickPhotoFromGallery() {
        val intent = Intent (Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityGallery.launch(intent)
    }

    private fun initDots() {
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
    }


}


