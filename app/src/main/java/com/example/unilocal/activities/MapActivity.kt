package com.example.unilocal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import com.example.unilocal.R
import com.example.unilocal.databinding.ActivityMapBinding
import com.example.unilocal.databinding.ActivitySearchResultBinding
import kotlin.math.log
import com.google.android.material.bottomnavigation.BottomNavigationView

class MapActivity : AppCompatActivity() {


    lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchText.setOnEditorActionListener { textView, i, keyEvent ->
            if( i == EditorInfo.IME_ACTION_SEARCH)
            {
                val search = binding.searchText.text.toString()

                if(search.isNotEmpty()){
                    val intent = Intent(baseContext, SearchResultActivity::class.java)
                    intent.putExtra("text", search)
                    startActivity(intent)
                }
            }
            true
        }





    }

}