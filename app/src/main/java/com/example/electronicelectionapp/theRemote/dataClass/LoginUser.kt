package com.example.electronicelectionapp.theRemote.dataClass

import com.google.gson.annotations.SerializedName

data class LoginUser(

    val refresh : String,
    val username: String,
    val password: String,
    val access : String,
    val your_title :String


    )
