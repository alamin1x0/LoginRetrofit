package com.developeralamin.valleysoftapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.developeralamin.valleysoftapp.R
import com.developeralamin.valleysoftapp.api.ApiInterface
import com.developeralamin.valleysoftapp.api.ApiUtilities
import com.developeralamin.valleysoftapp.databinding.ActivityLoginBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.userRegistion.setOnClickListener {
            startActivity(Intent(this, RegistionActivity::class.java))
        }

        binding.signIn.setOnClickListener {
            val emailId = binding.userEmail.text.toString()
            val password = binding.userPassword.text.toString()

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
                binding.userEmail.setError("Enter your Email")
                binding.userEmail.requestFocus()
            } else if (password.isEmpty()) {
                binding.userPassword.setError("Enter your Password")
                binding.userPassword.requestFocus()
            } else {

                val call =
                    ApiUtilities.getInstance().create(ApiInterface::class.java)
                        .loginUser(emailId, password)

                call.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {

                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Login Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                response.errorBody().toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(
                            this@LoginActivity,
                            t.localizedMessage.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })

            }

        }
    }
}