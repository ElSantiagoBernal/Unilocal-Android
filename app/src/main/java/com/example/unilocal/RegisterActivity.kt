package com.example.unilocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.unilocal.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var btnNext: Button
    lateinit var layouts:IntArray
    lateinit var adapter: Adapter
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.formPager
        btnNext = binding.Next

        layouts = intArrayOf(
            R.layout.activity_register_form_user_1,
            R.layout.activity_register_form_user_2

        )

        adapter = Adapter(this, layouts)
        viewPager.adapter = adapter

        binding.Next.setOnClickListener{
            nextListener()
        }


    }
    fun nextListener (){
        if (viewPager.currentItem+1 < layouts.size){
            viewPager.setCurrentItem(viewPager.currentItem+1)
        }
    }





}

