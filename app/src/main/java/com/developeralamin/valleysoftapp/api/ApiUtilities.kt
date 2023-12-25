package com.developeralamin.valleysoftapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {

    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.113:80/admin/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}