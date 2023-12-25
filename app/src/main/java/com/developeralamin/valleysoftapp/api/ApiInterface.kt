package com.developeralamin.valleysoftapp.api

import com.developeralamin.valleysoftapp.model.AllUserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("fetchusers.php")
    suspend fun getAllUserData(): Response<AllUserModel>

    @FormUrlEncoded
    @POST("register.php")
    fun creatUser(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String

    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String

    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("login.php")
    fun checkEmail(
        @Field("email") email: String,

    ): Call<ResponseBody>
}