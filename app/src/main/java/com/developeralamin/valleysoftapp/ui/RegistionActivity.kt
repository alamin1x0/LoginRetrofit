package com.developeralamin.valleysoftapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.developeralamin.valleysoftapp.R
import com.developeralamin.valleysoftapp.api.ApiInterface
import com.developeralamin.valleysoftapp.api.ApiUtilities
import com.developeralamin.valleysoftapp.databinding.ActivityRegistionBinding
import com.developeralamin.valleysoftapp.model.AllUserModel
import com.developeralamin.valleysoftapp.model.User
import com.developeralamin.valleysoftapp.model.UserRegistionModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegistionActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()


        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.RegisterBtn.setOnClickListener {

            val userName = binding.userName.text.toString()
            val emailId = binding.userEmail.text.toString()
            val password = binding.userPassword.text.toString()

            if (userName.isEmpty()) {
                binding.userName.setError("Enter your Username")
                binding.userName.requestFocus()
            } else if (emailId.isEmpty()) {
                binding.userEmail.setError("Enter your Email")
                binding.userEmail.requestFocus()
            } else if (password.isEmpty()) {
                binding.userPassword.setError("Enter your Password")
                binding.userPassword.requestFocus()
            } else {

                val call =
                    ApiUtilities.getInstance().create(ApiInterface::class.java)
                        .creatUser(userName, emailId, password)

                call.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {

                        if (response.isSuccessful) {
                            val data = response.body().toString()
                            Toast.makeText(
                                this@RegistionActivity,
                                "Registration Successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            Log.d("TAG", "onResponse: "+data.toString())

                            startActivity(Intent(this@RegistionActivity, HomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@RegistionActivity, "Error", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(
                            this@RegistionActivity,
                            t.localizedMessage.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })


//                call.enqueue(object : Callback<ResponseBody> {
//
//                    override fun onResponse(
//                        call: Call<ResponseBody>,
//                        response: Response<ResponseBody>
//                    ) {
//                        if (response.isSuccessful) {
//                            val data = response.body()
//
//                            Toast.makeText(this@RegistionActivity, data.toString(), Toast.LENGTH_SHORT).show()
//
//                            startActivity(Intent(this@RegistionActivity, HomeActivity::class.java))
//                        } else {
//                            // Handle unsuccessful registration response here
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                        Toast.makeText(this@RegistionActivity, "", Toast.LENGTH_SHORT).show()
//                    }
//                })

            }
        }

    }
}