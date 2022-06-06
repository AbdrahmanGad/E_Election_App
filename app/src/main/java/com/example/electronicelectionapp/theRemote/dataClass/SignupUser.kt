package com.example.electronicelectionapp.theRemote.dataClass


data class SignupUser(


        val response: String,
        val email: String,
        val username: String,
        val password: String,
        val password2: String,
        val fullname: String,
        val your_title: String,
        val id_number: Long,
        val phone_number: Long,
        val national_id: Long,
        val token : String
)