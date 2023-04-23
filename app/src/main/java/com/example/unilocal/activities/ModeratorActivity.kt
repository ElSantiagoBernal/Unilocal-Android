package com.example.unilocal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.unilocal.R
import com.example.unilocal.adapter.ViewPagerAdapter
import com.example.unilocal.databinding.ActivityModeratorBinding
import com.google.android.material.tabs.TabLayoutMediator

class ModeratorActivity : AppCompatActivity() {

    lateinit var binding:ActivityModeratorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModeratorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabs, binding.viewPager){ tab, pos ->
            when(pos){
                0 -> tab.text = "Lugares Pendientes"
                1 -> tab.text = "Lugares Aceptados"
                2 -> tab.text = "Lugares Rechazados"
            }
        }.attach()
    }
}