package com.example.electronicelectionapp.theRemote

import com.example.electronicelectionapp.theRemote.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ServiceApi by lazy {
        retrofit.create(ServiceApi::class.java)
    }

}