package com.example.unilocal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.unilocal.R
import com.example.unilocal.databinding.ActivityMapBinding
import com.example.unilocal.databinding.ActivitySearchResultBinding
import com.example.unilocal.fragment.TopSearchMenuFragment
import kotlin.math.log
import com.google.android.material.bottomnavigation.BottomNavigationView

class MapActivity : AppCompatActivity() {


    lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace<TopSearchMenuFragment>(R.id.top_search_menu)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

    }

}