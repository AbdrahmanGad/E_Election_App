package com.example.electronicelectionapp.Student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import com.example.electronicelectionapp.R
import kotlinx.android.synthetic.main.activity_st_settings.*
import kotlinx.android.synthetic.main.activity_sv_settings.*

class ST_Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st_settings)

        ST_back.setOnClickListener {
            onBackPressed()
        }
        Home.setOnClickListener {
            val intent = Intent(this@ST_Settings, ST_HomePage::class.java)
            startActivity(intent)
        }

    }
}