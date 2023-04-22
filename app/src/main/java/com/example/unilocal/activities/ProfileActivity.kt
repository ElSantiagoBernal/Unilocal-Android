package com.example.unilocal.activities

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.unilocal.R
import com.example.unilocal.databinding.ActivityLoginBinding
import com.example.unilocal.databinding.ActivityProfileBinding
import com.example.unilocal.db.Users

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val sp = getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val email = sp.getString("email_user", "").toString()

        user = Users.findByEmail(email)!!

        if(user != null){
            Toast.makeText(this, "uri: ${user.imgUrl}", Toast.LENGTH_SHORT).show()
            binding.imgUser.setImageURI(Uri.parse(user.imgUrl))
        }else{
            Toast.makeText(this, "Es null", Toast.LENGTH_SHORT).show()
        }*/

    }

    /*private val startForActivityGallery = registerForActivityResult(
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
        intent.type = ""
        Toast.makeText(this, "2", Toast.LENGTH_SHORT).show()
        startForActivityGallery.launch(intent)
    }*/
}