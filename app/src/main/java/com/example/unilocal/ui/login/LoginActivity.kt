package com.example.unilocal.ui.login

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.example.unilocal.databinding.ActivityLoginBinding

import com.example.unilocal.R
import com.example.unilocal.activities.*
import com.example.unilocal.db.Users



class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //val loading = binding.loading


        binding.forgot.setOnClickListener{goToForgotPass()}
        binding.registerNow.setOnClickListener{goToRegister()}
        binding.btnLogin.setOnClickListener { login() }

        /*loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }*/

    }

    private fun filter(){
        val filter = InputFilter { source, _, _, _, _, _ ->
            val regex = Regex("[a-zA-Z]+")
            if (source.toString().matches(regex)) {
                source
            } else {
                ""
            }
        }
    }

    private fun login(){
        var email = binding.email.text
        var password = binding.password.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){


                val user = Users.findByEmail(email.toString())
                if(user != null && user.password.equals(password)){
                    Toast.makeText(this,getString(R.string.login_msg_welcome)+ " ${user.nickname}",Toast.LENGTH_LONG).show()
                    finish()
                    goToMap()
                }else if(user == null) {
                Toast.makeText(this,getString(R.string.login_msg_user_do_not_exist),Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,getString(R.string.login_msg_pass_do_not_match),Toast.LENGTH_LONG).show()
                }

        }else{
            Toast.makeText(this,getString(R.string.register_user_msg_all_inpts_obligatories),Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    fun goToMap(){
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    fun goToDetailPlace(){
        val intent = Intent(this, DetailPlaceActivity::class.java)
        startActivity(intent)
    }

    fun goToWall(){
        val intent = Intent(this, WallActivity::class.java)
        startActivity(intent)
    }

    fun goToRegister(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun goToForgotPass(){
        val intent = Intent(this, ForgotPassActivity::class.java)
        startActivity(intent)
    }

    /* fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Cambia el color del texto a medida que el usuario escribe
        if (s.toString().isEmpty()) {
            myEditText.setTextColor(Color.GRAY) // Cambia el color del texto a gris si el texto está vacío
        } else {
            myEditText.setTextColor(Color.BLACK) // Cambia el color del texto a negro si el texto no está vacío
        }
    }*/
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })


}