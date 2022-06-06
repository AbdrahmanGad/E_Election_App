package com.example.electronicelectionapp.Supervisor

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import com.example.electronicelectionapp.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sv_settings.*

class SV_Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv_settings)

        back.setOnClickListener {
            onBackPressed()
        }

        Home.setOnClickListener {
            val intent = Intent(this@SV_Settings, SV_HomePage::class.java)
            startActivity(intent)
        }

        About.setOnClickListener {
//            val intent = Intent(this@SV_Settings, ::class.java)
//            startActivity(intent)
        }

        candidatesData.setOnClickListener {
            //https://www.surveymonkey.com/analyze/browse/J3DVpcH7AEjlE1SlE1Ov_2FOGVo2_2FxVoQCfpwzOTczQy0_3D
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.surveymonkey.com/home/?ut_source=header"))
            startActivity(intent)
        }

        addFutureElection.setOnClickListener {
            val intent = Intent(this@SV_Settings, SV_AddFutureElection::class.java)
            startActivity(intent)
        }




    }
}