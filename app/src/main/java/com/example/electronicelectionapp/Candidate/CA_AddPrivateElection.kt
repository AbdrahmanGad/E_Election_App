package com.example.electronicelectionapp.Candidate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.electronicelectionapp.R
import kotlinx.android.synthetic.main.activity_ca_add_private_election.*

class CA_AddPrivateElection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ca_add_private_election)

        CA_back.setOnClickListener {
            onBackPressed()
        }

        CA_setting.setOnClickListener {
            val intent = Intent(this@CA_AddPrivateElection, CA_Settings::class.java)
            startActivity(intent)
        }
    }
}