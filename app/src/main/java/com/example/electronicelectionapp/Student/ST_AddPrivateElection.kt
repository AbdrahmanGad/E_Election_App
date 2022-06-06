 package com.example.electronicelectionapp.Student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.electronicelectionapp.R
import kotlinx.android.synthetic.main.activity_st_add_private_election.*

 class ST_AddPrivateElection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st_add_private_election)

        ST_back.setOnClickListener {
            onBackPressed()
        }

        ST_setting.setOnClickListener {
            val intent = Intent(this@ST_AddPrivateElection, ST_Settings::class.java)
            startActivity(intent)
        }

    }
}