package com.example.unilocal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.unilocal.Adapter
import com.example.unilocal.R
import com.example.unilocal.databinding.*

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPassBinding
    private lateinit var binding_form_1: ActivityForgotPassForm1Binding
    private lateinit var binding_form_2: ActivityForgotPassForm2Binding
    lateinit var viewPager: ViewPager
    lateinit var layouts:IntArray
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        binding_form_1 = ActivityForgotPassForm1Binding.inflate(layoutInflater)
        binding_form_2 = ActivityForgotPassForm2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.formPagerForgot
        layouts = intArrayOf(
            R.layout.activity_forgot_pass_form_1,
            R.layout.activity_forgot_pass_form_2
        )

        adapter = Adapter(this, layouts, null)
        binding.formPagerForgot.adapter = adapter

        binding.formPagerForgot.findViewById<Button>(R.id.btn_send).setOnClickListener{
            nextListener()
        }
    }

    fun nextListener (){
        if (viewPager.currentItem < layouts.size){
            viewPager.setCurrentItem(viewPager.currentItem)
        }
    }
}