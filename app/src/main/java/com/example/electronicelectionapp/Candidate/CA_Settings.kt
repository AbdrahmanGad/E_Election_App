package com.example.electronicelectionapp.Candidate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Student.ST_HomePage
import kotlinx.android.synthetic.main.activity_ca_settings.*
import kotlinx.android.synthetic.main.activity_st_settings.*

class CA_Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ca_settings)

        back.setOnClickListener {
            onBackPressed()
        }
        Home.setOnClickListener {
            val intent = Intent(this@CA_Settings, CA_HomePage::class.java)
            startActivity(intent)
        }

//        val bundel: Bundle? = intent.extras
//        val eCandidateUsername = bundel!!.getInt("CandidateUsername")

        checkForAnyFutureElection.setOnClickListener {
            val intent = Intent(this@CA_Settings, CA_FutureElections::class.java)
//            intent.putExtra("CandidateUsername", eCandidateUsername)
            startActivity(intent)
        }

    }
}