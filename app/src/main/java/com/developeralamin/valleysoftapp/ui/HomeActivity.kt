package com.developeralamin.valleysoftapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.developeralamin.valleysoftapp.adapter.AllUserAdapter
import com.developeralamin.valleysoftapp.api.ApiInterface
import com.developeralamin.valleysoftapp.api.ApiUtilities
import com.developeralamin.valleysoftapp.databinding.ActivityHomeBinding
import com.developeralamin.valleysoftapp.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    private lateinit var list: List<User>
    private lateinit var adapter: AllUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = listOf()

        adapter = AllUserAdapter(this, list)
        binding.allUserShow.adapter = adapter


        lifecycleScope.launch(Dispatchers.IO) {

            val res = ApiUtilities.getInstance().create(ApiInterface::class.java).getAllUserData()

            withContext(Dispatchers.Main) {
                binding.allUserShow.adapter =
                    AllUserAdapter(this@HomeActivity, res.body()!!.users)

                binding.progressBar.visibility = View.GONE

                Log.d("alluserdata", "user show"+res.body()!!)
            }

        }
    }
}