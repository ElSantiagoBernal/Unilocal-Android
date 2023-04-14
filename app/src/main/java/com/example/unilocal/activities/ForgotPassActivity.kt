package com.example.unilocal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.unilocal.Adapter
import com.example.unilocal.R
import com.example.unilocal.databinding.*
import com.example.unilocal.db.Users
import com.example.unilocal.model.User

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPassBinding
    private lateinit var binding_form_1: ActivityForgotPassForm1Binding
    private lateinit var binding_form_2: ActivityForgotPassForm2Binding
    lateinit var viewPager: ViewPager
    lateinit var layouts:IntArray
    lateinit var adapter: Adapter
    val dots = mutableListOf<TextView>()
    lateinit var btn_send:Button


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
        //layouts.plus(R.layout.activity_forgot_pass_form_2)

        adapter = Adapter(this, layouts, null)
        binding.formPagerForgot.adapter = adapter

        btn_send = binding.btnSend

        initDots()

        btn_send.setOnClickListener{
            verifyAllInputs()
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                dots.forEachIndexed { index, dot ->
                    dot.setTextColor(
                        ContextCompat.getColor( this@ForgotPassActivity,
                            if (index == position) R.color.active else R.color.inactive
                        )
                    )
                }
                if(position==1) {
                    verifyEmailInput()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })


    }

    private fun verifyAllInputs() {
        var email = viewPager.findViewById<EditText>(R.id.user_email).text.toString()
        var pass = viewPager.findViewById<EditText>(R.id.user_new_pass).text.toString()
        var confirm_pass = viewPager.findViewById<EditText>(R.id.user_repeat_pass).text.toString()

        if(email.isNotEmpty() && pass.isNotEmpty() && confirm_pass.isNotEmpty()) {
            if(pass.equals(confirm_pass)){
                var user = Users.findByEmail(email)
                if (user != null) {
                    user.password = pass
                    Toast.makeText(this, getString(R.string.forgot_msg_pass_changed), Toast.LENGTH_SHORT).show()
                    Toast.makeText(this,"New Pass ${user.password}", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, getString(R.string.forgot_msg_passwords_do_not_match), Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, getString(R.string.forgot_msg_obligatorie_all_inputs), Toast.LENGTH_SHORT).show()
        }
    }


    private fun verifyEmailDb() {
        var email = viewPager.findViewById<EditText>(R.id.user_email).text.toString()
        if(email.isNotEmpty()){
            var user = Users.findByEmail(email)
            if(user == null){
                Toast.makeText(this, getString(R.string.login_msg_user_do_not_exist), Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, getString(R.string.forgot_msg_email_registered), Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, getString(R.string.forgot_msg_obligatorie_email_input), Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyEmailInput() {
        var email = viewPager.findViewById<EditText>(R.id.user_email).text.toString()
        if(email.isEmpty()){
            Toast.makeText(this, getString(R.string.forgot_msg_obligatorie_email_input), Toast.LENGTH_SHORT).show()
        }else{
            verifyEmailDb()
        }
    }



    fun nextListener (){
        if (viewPager.currentItem < layouts.size){
            viewPager.setCurrentItem(viewPager.currentItem)
        }
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