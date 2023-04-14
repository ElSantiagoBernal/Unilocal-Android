package com.example.unilocal.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.unilocal.R
import com.example.unilocal.databinding.ActivityMapBinding
import com.example.unilocal.databinding.ActivitySearchResultBinding
import com.example.unilocal.fragment.HomeFragment
import com.example.unilocal.fragment.MapFragment
import com.example.unilocal.fragment.ProfileFragment
import com.example.unilocal.fragment.TopSearchMenuFragment
import kotlin.math.log
import com.google.android.material.bottomnavigation.BottomNavigationView

class MapActivity : AppCompatActivity() {

    lateinit var binding: ActivityMapBinding
    private var MENU_MAP = "map"
    private var MENU_HOME = "home"
    private var MENU_ADD_PLACE = "add_place"
    private var MENU_PROFILE = "home"
    private var MENU_SETTINGS = "home"

    lateinit var navigationView:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace<TopSearchMenuFragment>(R.id.top_search_menu)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        navigationView = findViewById(R.id.bottom_app_bar)

        navigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.map -> changeFragment(1, MENU_MAP)
                R.id.home -> changeFragment(2, MENU_HOME)
                R.id.profile -> changeFragment(4, MENU_PROFILE)
            }
            true
        }



    }

    fun changeFragment(valor:Int, nombre:String){
        var fragment:Fragment = when(valor){
            1 -> MapFragment()
            2 -> HomeFragment()
            else -> ProfileFragment()
        }
        supportFragmentManager.beginTransaction()
            .replace(binding.mainContent.id, fragment)
            .addToBackStack(nombre).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val count = supportFragmentManager.backStackEntryCount

        if(count > 0){
            val nombre = supportFragmentManager.getBackStackEntryAt(count - 1).name
            when(nombre){
                MENU_MAP -> binding.bottomAppBar.menu.getItem(0).isChecked = true
                MENU_HOME -> binding.bottomAppBar.menu.getItem(1).isChecked = true
                MENU_PROFILE -> binding.bottomAppBar.menu.getItem(3).isChecked = true
            }
        }
    }

    fun logOut(){
        val sh = getSharedPreferences("sesion", Context.MODE_PRIVATE).edit()
        sh.clear()
        sh.commit()
        finish()
    }
}