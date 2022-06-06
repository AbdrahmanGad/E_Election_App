package com.example.electronicelectionapp.Supervisor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.electronicelectionapp.R
import kotlinx.android.synthetic.main.activity_sv_preview.*
import kotlinx.android.synthetic.main.activity_sv_settings.*
import kotlinx.android.synthetic.main.activity_sv_settings.back

class SV_Preview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv_preview)

        back.setOnClickListener {
            onBackPressed()
        }

        setting.setOnClickListener {
            val intent = Intent(this@SV_Preview, SV_Settings::class.java)
            startActivity(intent)
        }

    }
}